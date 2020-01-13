package com.skyler.cobweb.config;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Description: 日期格式校验
 * <pre>
 *
 * </pre>
 * <p>
 * NB.
 * </p>
 * Created by skyler on 2019-07-30 at 18:09
 */
@Slf4j
public class DateMatchValidator implements ConstraintValidator<DateMatch, CharSequence> {

    private static final String FORMAT = "yyyy-MM-dd";

    /**
     * 是否可以转成yyyy-MM-dd HH:mm:ss日期
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        try {
            LocalDate.parse(value, DateTimeFormatter.ofPattern(FORMAT));
            return true;
        } catch (Exception e) {
            log.warn("数据:{}不能转成日期", value);
        }
        return false;
    }
}
