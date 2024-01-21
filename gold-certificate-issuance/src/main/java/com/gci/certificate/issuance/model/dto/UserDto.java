package com.gci.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import lombok.Builder;
import lombok.Data;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.Set;


@Data
@Builder
public class UserDto {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Set<CredentialRepresentation> credentials;
    private boolean enabled;
}
