package com.gci.certificate.issuance.model.annotation;

import com.gci.certificate.issuance.validator.NameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */
@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Name {
    String message() default "Invalid name format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
