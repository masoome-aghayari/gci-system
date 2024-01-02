package com.gci.authentication.model;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private Date date;
    private String path;
    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message, HttpStatus httpStatus, Date date, String path) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.date = date;
        this.path = path;
    }
}
