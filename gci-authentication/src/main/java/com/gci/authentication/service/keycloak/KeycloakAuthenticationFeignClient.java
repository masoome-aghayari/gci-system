package com.gci.authentication.service.keycloak;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import feign.Headers;
import feign.Response;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "keycloak", url = "http://localhost:8180/realms/gci_test/protocol/openid-connect")
public interface KeycloakAuthenticationFeignClient {

    @RequestMapping(value = "/token", method = RequestMethod.POST,
            consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    AccessTokenResponse getToken(@RequestBody Map<String, ?> formData);

    @RequestMapping(value = "/logout", method = RequestMethod.POST,
            consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Response logout(@RequestBody Map<String, ?> formData);

}
