package com.gci.authentication.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import lombok.Data;

@Data
public class LogoutDto {
    private String refreshToken;
    private String accessToken;
}
