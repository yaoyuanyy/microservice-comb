package com.skyler.cobweb.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static com.skyler.cobweb.config.LengthAndPatternMatch.CharType.ALLEPX;

/**
 * Description: 校验目标字符串长度和格式
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2019-09-12 at 16:52
 */
@Constraint(validatedBy = LengthAndPatternMatchValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Inherited
@Documented
public @interface LengthAndPatternMatch {

    int length() default -1;

    CharType charType() default ALLEPX;

    String message() default "{constraint.default.const.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    public static enum CharType{
        /**
         * 中英文大小写
         */
        ZHONGYINGWEN("^[\\u4e00-\\u9fa5_a-zA-Z]+$"),

        /**
         * 中英文大小写和数字
         */
        ZHONGYINGWEN_NUMBER("^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$"),

        /**
         * 没有限制
         */
        ALLEPX("");

        private String pattern;

        CharType(String pattern) {
            this.pattern = pattern;
        }

        public String pattern() {
            return pattern;
        }
    }
}
