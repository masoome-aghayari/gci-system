package com.gci.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationToken {
    private String access_token;
    private String refreshToken;
    private String token_type;
    private String session_state;
    private String scope;
    private Long expires_in;
    private Long refresh_expires_in;
}
