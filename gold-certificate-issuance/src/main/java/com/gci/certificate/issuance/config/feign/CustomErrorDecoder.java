package com.gci.certificate.issuance.config.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.*;
import org.apache.http.auth.AuthenticationException;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 401 -> new AuthenticationException();
            case 403 -> new NotAuthorizedException(response.reason());
            case 404 -> new NotFoundException();
            case 405 -> new NotAllowedException(jakarta.ws.rs.core.Response.status(response.status()).build());
            case 406 -> new NotAcceptableException();
            case 503 -> new ServiceUnavailableException();
            default -> new Exception(response.toString());
        };
    }
}