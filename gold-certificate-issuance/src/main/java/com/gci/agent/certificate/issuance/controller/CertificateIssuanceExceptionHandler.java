package com.gci.agent.certificate.issuance.controller;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

import com.gci.agent.certificate.issuance.exceptions.DuplicateGoldException;
import com.gci.agent.certificate.issuance.exceptions.NoSuchWorkshopFoundException;
import com.gci.agent.certificate.issuance.exceptions.RequestNotFoundException;
import com.gci.agent.certificate.issuance.model.ErrorResponse;
import jakarta.ws.rs.ServiceUnavailableException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Date;

@RestControllerAdvice
public class CertificateIssuanceExceptionHandler {

    @ExceptionHandler({MethodValidationException.class, TypeMismatchException.class, MissingPathVariableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleMethodValidationException(Exception ex, WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getContextPath());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED, request.getContextPath());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN, request.getContextPath());
    }

    @ExceptionHandler({RequestNotFoundException.class, NoResourceFoundException.class, NoSuchWorkshopFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> requestNotFoundExceptionHandler(Exception ex, WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getContextPath());
    }

    @ExceptionHandler({MethodNotAllowedException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorResponse> methodNotAllowedExceptionHandler(Exception ex, WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED, request.getContextPath());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected ResponseEntity<ErrorResponse> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                             WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, request.getContextPath());
    }

    @ExceptionHandler(DuplicateGoldException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> duplicateGoldExceptionHandler(DuplicateGoldException ex, WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.CONFLICT, request.getContextPath());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                         WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, request.getContextPath());
    }


    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> generalExceptionHandler(Exception ex, WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getContextPath());
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<ErrorResponse> handleServiceUnavailableException(ServiceUnavailableException ex,
                                                                           WebRequest request) {
        return getErrorResponse(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, request.getContextPath());
    }

    private ResponseEntity<ErrorResponse> getErrorResponse(String message, HttpStatus status, String path) {
        var errorResponse = new ErrorResponse(message, status, new Date(), path);
        return new ResponseEntity<>(errorResponse, status);
    }
}
