package com.gci.authentication.service;

import com.gci.authentication.service.keycloak.KeycloakAuthenticationFeignClient;
import com.gci.authentication.service.keycloak.KeycloakRegistrationFeignClient;
import com.gci.authentication.service.keycloak.UserServiceTestsDataProvider;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

@SpringBootTest
class UserServiceTest extends UserServiceTestsDataProvider {

    @Autowired
    UserService userService;

    @MockBean
    KeycloakAuthenticationFeignClient authenticationFeignClient;

    @MockBean
    KeycloakRegistrationFeignClient registrationFeignClient;

    @Test
    void given_validLoginRequestBody_when_required_data_is_provided_then_returns_expectedResponse() {
        var expectedResponse = getAccessTokenResponse();
        Map<String, ?> loginRequestBody = getValidLoginRequestBodyMap();
        Mockito.when(authenticationFeignClient.getToken(loginRequestBody)).thenReturn(expectedResponse);
        assertEquals(expectedResponse, authenticationFeignClient.getToken(loginRequestBody));
    }

    @Test
    void given_loginRequestBody_when_required_data_is_not_provided_then_throws_FeignClientException() {
        Map<String, ?> loginRequestBody = getValidLoginRequestBodyMap();
        Mockito.when(authenticationFeignClient.getToken(loginRequestBody)).thenThrow(FeignException.FeignClientException.class);
        assertThrows(FeignException.FeignClientException.class,
                () -> authenticationFeignClient.getToken(getInvalidLoginRequestBody()));
    }

    @Test
    void logoutUser() {
    }

    @Test
    void refreshToken() {
    }

    @Test
    void createUser() {
    }
}