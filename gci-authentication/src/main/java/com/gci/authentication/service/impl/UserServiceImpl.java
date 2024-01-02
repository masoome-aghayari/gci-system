package com.gci.authentication.service.impl;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import com.gci.authentication.config.keycloak.KeycloakProvider;
import com.gci.authentication.model.UserRepresentation;
import com.gci.authentication.model.dto.LoginDto;
import com.gci.authentication.model.dto.LogoutDto;
import com.gci.authentication.model.dto.UserDto;
import com.gci.authentication.service.UserService;
import com.gci.authentication.service.keycloak.KeycloakAuthenticationFeignClient;
import com.gci.authentication.service.keycloak.KeycloakRegistrationFeignClient;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final KeycloakProvider keycloakProvider;
    private final KeycloakAuthenticationFeignClient authenticationFeignClient;
    private final KeycloakRegistrationFeignClient registrationFeignClient;

    public void createUser(UserDto userDTO) {
        var adminLoginResponse = loginAdmin();
        var userRepresentation = getUserRepresentation(userDTO);
        registrationFeignClient.registerUser(getHttpHeaders(adminLoginResponse.getToken()), userRepresentation);
    }

    @Override
    public AccessTokenResponse loginAgent(LoginDto loginDto) throws BadRequestException {
        return authenticationFeignClient.getToken(getLoginRequestBodyMap(loginDto));
    }

    private AccessTokenResponse loginAdmin() {
        return authenticationFeignClient.getToken(getAdminLoginRequestBodyMap());
    }


    @Override
    public AccessTokenResponse refreshToken(String refreshToken) {
        return authenticationFeignClient.getToken(getRefreshTokenRequestBodyMap(refreshToken));
    }

    @Override
    public void logoutUser(LogoutDto logoutDto) {
        var httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(logoutDto.getAccessToken());
        authenticationFeignClient.logout(getLogoutRequestBodyMap(logoutDto.getRefreshToken()));
    }

    private HttpHeaders getHttpHeaders(String adminToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


    private UserRepresentation getUserRepresentation(UserDto userDTO) {
        CredentialRepresentation credential = createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        return user;
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    private Map<String, ?> getLoginRequestBodyMap(LoginDto loginDto) {
        Map<String, String> formData = new HashMap<>();
        formData.put("username", loginDto.getUsername());
        formData.put("password", loginDto.getPassword());
        formData.put("client_id", keycloakProvider.getAgentClientId());
        formData.put("client_secret", keycloakProvider.getAgentClientSecret());
        formData.put("grant_type", keycloakProvider.getAccessTokenGrantType());
        return formData;
    }

    private Map<String, ?> getAdminLoginRequestBodyMap() {
        Map<String, String> formData = new HashMap<>();
        formData.put("username", keycloakProvider.getAdminUsername());
        formData.put("password", keycloakProvider.getAdminPassword());
        formData.put("client_id", keycloakProvider.getAdminClientId());
        formData.put("client_secret", keycloakProvider.getAdminClientSecret());
        formData.put("grant_type", keycloakProvider.getAccessTokenGrantType());
        return formData;
    }

    private Map<String, String> getLogoutRequestBodyMap(String refreshToken) {
        Map<String, String> formData = new HashMap<>();
        formData.put("refresh_token", refreshToken);
        formData.put("client_id", keycloakProvider.getAgentClientId());
        formData.put("client_secret", keycloakProvider.getAgentClientSecret());
        return formData;
    }

    private Map<String, String> getRefreshTokenRequestBodyMap(String refreshToken) {
        Map<String, String> formData = getLogoutRequestBodyMap(refreshToken);
        formData.put("grant_type", keycloakProvider.getRefreshTokenGrantType());
        return formData;
    }
}
