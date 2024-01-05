package com.gci.agent.certificate.issuance.exceptions;

/*
 * @author masoome.aghayari
 * @since 12/30/23
 */

public class InvalidTrackingCodeException extends Exception {
    public InvalidTrackingCodeException(String message, Exception cause) {
        super(message, cause);
    }

    public InvalidTrackingCodeException(String message) {
        super(message       );
    }
}
