package com.gci.authentication.service.keycloak;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import com.gci.authentication.model.UserRepresentation;
import feign.Response;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "keycloak", url = "http://localhost:8180/admin/realms/gci_test/users")
public interface KeycloakRegistrationFeignClient {

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    void registerUser(@RequestHeader HttpHeaders headers, @RequestBody UserRepresentation userRepresentation);
}
