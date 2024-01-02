package com.gci.authentication.service;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import com.gci.authentication.model.dto.LogoutDto;
import com.gci.authentication.model.dto.UserDto;
import com.gci.authentication.model.dto.LoginDto;
import feign.Response;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    AccessTokenResponse loginAgent(LoginDto loginDto);

    void logoutUser(LogoutDto logoutDto);

    AccessTokenResponse refreshToken(String refreshToken);

    void createUser(UserDto userDTO);
}
