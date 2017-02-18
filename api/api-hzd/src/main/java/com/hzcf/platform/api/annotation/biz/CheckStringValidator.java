package com.hzcf.platform.api.annotation.biz;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 * Created by leijiaming on 2017/2/18
 *
 */
public class CheckStringValidator implements ConstraintValidator<CheckString, String> {

    private Pattern pattern;

    private long min = 0;

    private long max = 0;

    @Override
    public void initialize(CheckString constraintAnnotation) {

        pattern = Pattern.compile(constraintAnnotation.regexp(), CASE_INSENSITIVE);
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value))
            return false;
        int length = value.length();
        if (length < min || length > max)
            return false;
        return matchPart(value, pattern);
    }

    private boolean matchPart(String part, Pattern pattern) {
        Matcher matcher = pattern.matcher(part);
        return matcher.matches();
    }

}
