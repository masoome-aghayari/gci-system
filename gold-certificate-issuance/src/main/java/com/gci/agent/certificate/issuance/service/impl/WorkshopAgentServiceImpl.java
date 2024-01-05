package com.gci.agent.certificate.issuance.service.impl;

/*
 * @author masoome.aghayari
 * @since 1/1/24
 */

import com.gci.agent.certificate.issuance.model.dto.*;
import com.gci.agent.certificate.issuance.model.dto.WorkshopAgentDto;
import com.gci.agent.certificate.issuance.service.WorkshopAgentService;
import com.gci.agent.certificate.issuance.service.feign.AuthenticationFeignClient;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class WorkshopAgentServiceImpl implements WorkshopAgentService {
    private final AuthenticationFeignClient authenticationFeignClient;

    @Override
    public void register(WorkshopAgentDto workshopAgentDto) {
        authenticationFeignClient.registerUser(getUserDto(workshopAgentDto));
    }

    @Override
    public AccessTokenResponse login(LoginDto loginDto) {
        return authenticationFeignClient.getToken(loginDto);
    }

    @Override
    public AccessTokenResponse refreshToken(RefreshTokenDto refreshTokenDto) {
        return authenticationFeignClient.refreshToken(refreshTokenDto);
    }

    @Override
    public void logout(LogoutDto logoutDto) {
        var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        var httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(request.getHeader("Authorization"));
        authenticationFeignClient.logout(httpHeaders, logoutDto);
    }

    private UserDto getUserDto(WorkshopAgentDto agentDto) {
        return UserDto.builder().firstname(agentDto.getName())
                .lastname(agentDto.getSurname())
                .password(agentDto.getPassword())
                .username(agentDto.getUsername())
                .credentials(Set.of(getCredential(agentDto)))
                .build();
    }

    private CredentialRepresentation getCredential(WorkshopAgentDto agentDto) {
        var credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(agentDto.getPassword());
        return credential;
    }
}
