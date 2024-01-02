package com.gci.authentication.model.enums;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Endpoints {
    public static final String BASE_URL = "/users";
    public static final String LOGIN = "/login";
    public static final String LOGOUT ="/logout";
    public static final String REFRESH_TOKEN = "/token/refresh";
}
