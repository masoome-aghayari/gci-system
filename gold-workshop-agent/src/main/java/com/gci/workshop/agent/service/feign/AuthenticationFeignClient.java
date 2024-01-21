package com.gci.workshop.agent.service.feign;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import com.gci.workshop.agent.model.dto.LoginDto;
import com.gci.workshop.agent.model.dto.LogoutDto;
import com.gci.workshop.agent.model.dto.RefreshTokenDto;
import com.gci.workshop.agent.model.dto.UserDto;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${feign.client.name}", url = "http://localhost:8082/api/users")
public interface AuthenticationFeignClient {
    String MEDIA_TYPE = "application/json";
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String REFRESH_TOKEN = "/token/refresh";

    @PostMapping(consumes = MEDIA_TYPE, produces = MEDIA_TYPE)
    void registerUser(UserDto userDto);

    @PostMapping(value = LOGIN, consumes = MEDIA_TYPE, produces = MEDIA_TYPE)
    AccessTokenResponse getToken(@RequestBody LoginDto loginDto);

    @PostMapping(value = LOGOUT, consumes = MEDIA_TYPE, produces = MEDIA_TYPE)
    void logout(@RequestHeader HttpHeaders headers, @RequestBody LogoutDto logoutDto);

    @PostMapping(value = REFRESH_TOKEN, consumes = MEDIA_TYPE, produces = MEDIA_TYPE)
    AccessTokenResponse refreshToken(@RequestBody RefreshTokenDto refreshTokenDto);

}
