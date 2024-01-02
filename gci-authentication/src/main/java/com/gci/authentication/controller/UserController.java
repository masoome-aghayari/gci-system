package com.gci.authentication.controller;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import com.gci.authentication.model.dto.LoginDto;
import com.gci.authentication.model.dto.LogoutDto;
import com.gci.authentication.model.dto.RefreshTokenDto;
import com.gci.authentication.model.dto.UserDto;
import com.gci.authentication.model.enums.Endpoints;
import com.gci.authentication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping(Endpoints.BASE_URL)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserDto userDTO) {
        userService.createUser(userDTO);
    }

    @PostMapping(value = Endpoints.LOGIN)
    public AccessTokenResponse login(@RequestBody LoginDto loginDto) {
        return userService.loginAgent(loginDto);
    }

    @GetMapping(value = Endpoints.REFRESH_TOKEN)
    public AccessTokenResponse refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return userService.refreshToken(refreshTokenDto.getRefreshToken());
    }

    @PostMapping(value = Endpoints.LOGOUT)
    public void logout(@RequestBody LogoutDto logoutDto) {
        var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logoutDto.setAccessToken(request.getHeader("Authorization"));
        userService.logoutUser(logoutDto);
    }
}
