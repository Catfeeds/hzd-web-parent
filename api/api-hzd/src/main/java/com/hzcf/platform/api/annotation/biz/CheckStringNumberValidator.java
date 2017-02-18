package com.hzcf.platform.api.annotation.biz;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 * Created by leijiaming on 2017/2/18
 */
public class CheckStringNumberValidator implements ConstraintValidator<CheckStringNumber, String> {

    private Pattern pattern = Pattern.compile("^[0-9]+([.]{1}[0-9]+){0,1}$", CASE_INSENSITIVE);

    private long min = 0;

    private long max = 0;

    @Override
    public void initialize(CheckStringNumber constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value))
            return false;
        if (!matchPart(value, pattern))
            return false;
        BigDecimal bc = new BigDecimal(value);
        return bc.intValue() >= min && bc.intValue() <= max;
    }

    private boolean matchPart(String part, Pattern pattern) {
        Matcher matcher = pattern.matcher(part);
        return matcher.matches();
    }

}
