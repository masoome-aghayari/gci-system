package com.gci.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
