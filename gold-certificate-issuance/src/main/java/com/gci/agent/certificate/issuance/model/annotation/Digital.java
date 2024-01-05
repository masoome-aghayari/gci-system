package com.gci.agent.certificate.issuance.model.annotation;

import com.gci.agent.certificate.issuance.validator.DigitalValidator;
import com.gci.agent.certificate.issuance.validator.NameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

/*
 * @author masoome.aghayari
 * @since 1/5/24
 */
@Documented
@Constraint(validatedBy = DigitalValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Digital {
    String message() default "just digits are allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int digitsCount() default 8;

}
