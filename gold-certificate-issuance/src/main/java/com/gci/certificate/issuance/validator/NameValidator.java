package com.gci.certificate.issuance.validator;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.certificate.issuance.model.annotation.Name;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;

import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name, String> {
    private static final String PERSIAN_NAME_REGEX = "^[آ-ی]{3,}$";
    private static final String LATIN_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private static final Pattern PERSIAN_PATTERN = Pattern.compile(PERSIAN_NAME_REGEX);
    private static final Pattern LATIN_PATTERN = Pattern.compile(LATIN_NAME_REGEX);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.isNotBlank(value) &&
               (PERSIAN_PATTERN.matcher(value).matches() || LATIN_PATTERN.matcher(value).matches());
    }
}
