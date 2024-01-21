package com.gci.workshop.agent.validator;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.workshop.agent.model.annotation.NationalCode;
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

            int length = nationalCode.length();
            if (hasInValidLengthOrDigits(nationalCode))
                return false;
            else {
                nationalCode = length == 8 ? "00" + nationalCode : length == 9 ? "0" + nationalCode : nationalCode;
                int calculatedLastDigit = calculateNationalCodeLastDigit(nationalCode);
                int actualLastDigit = nationalCode.charAt(9) - 48;
                return calculatedLastDigit == actualLastDigit;
            }
    }

    public boolean hasInValidLengthOrDigits(String nationalCode) {
        return (!nationalCode.matches("[0-9]{8,10}") ||
                (nationalCode.matches("0{8,10}|1{8,10}|2{8,10}|3{8,10}|4{8,10}|5{8,10}|6{8,10}|7{8,10}|8{8,10}|9{8,10}")));
    }

    public int calculateNationalCodeLastDigit(String nationalCode) throws StringIndexOutOfBoundsException {
        int sum = IntStream.range(0, 9)
                .map(i -> Character.getNumericValue(nationalCode.charAt(i)) * (10 - i))
                .sum();
        int remainder = sum % 11;
        return remainder < 2 ? remainder : 11 - remainder;
    }
}
