package com.gci.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

import lombok.Getter;
import lombok.Setter;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse extends ErrorMessage {
    private HttpStatus httpStatus;
    private Date date;
    private String path;

    public ErrorResponse(String message) {
        super(message);
    }

    public ErrorResponse(String message, HttpStatus httpStatus, Date date, String path) {
        super(message);
        this.httpStatus = httpStatus;
        this.date = date;
        this.path = path;
    }
}
