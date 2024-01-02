package com.gci.authentication.config.keycloak;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@NoArgsConstructor
public class KeycloakProvider {

    @Value("${keycloak.auth-server-url}")
    private String serverURL;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin-client-id}")
    private String adminClientId;

    @Value("${keycloak.admin-client-secret}")
    private String adminClientSecret;

    @Value("${keycloak.agent-client-id}")
    private String agentClientId;

    @Value("${keycloak.agent-client-secret}")
    private String agentClientSecret;

    @Value("${keycloak.grant-type.refresh}")
    private String refreshTokenGrantType;

    @Value("${keycloak.grant-type.access}")
    private String accessTokenGrantType;

    @Value("${keycloak.admin.password}")
    private String adminPassword;

    @Value("${keycloak.admin.username}")
    private String adminUsername;
}