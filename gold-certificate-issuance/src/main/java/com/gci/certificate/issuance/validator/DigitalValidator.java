package com.gci.certificate.issuance.validator;

/*
 * @author masoome.aghayari
 * @since 1/5/24
 */

import com.gci.certificate.issuance.model.annotation.Digital;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DigitalValidator implements ConstraintValidator<Digital, String> {
    private int digitsCount;

    @Override
    public void initialize(Digital constraintAnnotation) {
        digitsCount = constraintAnnotation.digitsCount();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        return hasValidLength(code) && hasValidFormat(code);
    }

    private boolean hasValidFormat(String trackingCode) {
        boolean result = true;
        try {
            Integer.parseInt(trackingCode);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    private boolean hasValidLength(String trackingCode) {
        return trackingCode.length() != digitsCount;
    }
}
