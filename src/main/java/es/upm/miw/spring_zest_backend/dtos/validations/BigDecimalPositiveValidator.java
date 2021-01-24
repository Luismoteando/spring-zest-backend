package es.upm.miw.spring_zest_backend.dtos.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class BigDecimalPositiveValidator implements ConstraintValidator<BigDecimalPositive, BigDecimal> {

    @Override
    public void initialize(BigDecimalPositive constraint) {
        // Empty, not operation
    }

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext context) {
        return bigDecimal != null && bigDecimal.signum() != -1;
    }

}
