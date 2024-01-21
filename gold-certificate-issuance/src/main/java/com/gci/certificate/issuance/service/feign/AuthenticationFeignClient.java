package com.gci.certificate.issuance.service.feign;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import com.gci.certificate.issuance.model.dto.LoginDto;
import com.gci.certificate.issuance.model.dto.LogoutDto;
import com.gci.certificate.issuance.model.dto.RefreshTokenDto;
import com.gci.certificate.issuance.model.dto.UserDto;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${feign.client.name}", url = "http://localhost:8082/api/users")
public interface AuthenticationFeignClient {

    @PostMapping(consumes = "application/json", produces = "application/json")
    void registerUser(UserDto userDto);

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    AccessTokenResponse getToken(@RequestBody LoginDto loginDto);

    @RequestMapping(value = "/logout", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    void logout(@RequestHeader HttpHeaders headers, @RequestBody LogoutDto logoutDto);

    @RequestMapping(value = "/token/refresh", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    AccessTokenResponse refreshToken(@RequestBody RefreshTokenDto refreshTokenDto);

}
