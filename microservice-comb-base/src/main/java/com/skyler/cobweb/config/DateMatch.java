package com.skyler.cobweb.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Description:
 * <p>
 * 被注解标注的元素必须是yyyy-MM-dd HH:mm:ss格式的日期字符串. Accepts {@code CharSequence}.
 *
 * Created by skyler on 2019-07-30 at 18:07
 */
@Constraint(validatedBy = DateMatchValidator.class)
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateMatch {

    String message() default "{constraint.default.const.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

