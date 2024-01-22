package com.gci.workshop.agent.model.annotation;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.workshop.agent.validator.NationalCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NationalCodeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface NationalCode {
    String message() default "Invalid nationalCode ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
