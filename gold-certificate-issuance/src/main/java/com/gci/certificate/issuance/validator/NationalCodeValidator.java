package com.gci.certificate.issuance.validator;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.certificate.issuance.model.annotation.NationalCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;

import java.util.stream.IntStream;

public class NationalCodeValidator implements ConstraintValidator<NationalCode, String> {
    @Override
    public boolean isValid(String nationalCode, ConstraintValidatorContext context) {
        return Strings.isNotBlank(nationalCode) && nationalCode.matches("\\d{10}") && isNationalCode(nationalCode);
    }

    private boolean isNationalCode(String nationalCode) {
        int sum = IntStream.range(0, 9).map(i -> ((int) nationalCode.charAt(i)) * (10 - i)).sum();
        int remainder = sum % 11;
        int lastDigit = remainder < 2 ? remainder : 11 - (remainder);
        return (int) nationalCode.charAt(9) == lastDigit;
    }
}
