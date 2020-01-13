package com.skyler.cobweb.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 验证字符串的长度和格式
 * <pre>
 *  格式目前支持
 *  1. 中文大小写
 *  2. 无限制
 *  3. 长度小于xxx
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2019-09-12 at 16:56
 */
@Slf4j
public class LengthAndPatternMatchValidator implements ConstraintValidator<LengthAndPatternMatch, CharSequence> {

    /**
     * 长度
     */
    private int length;

    /**
     * 格式
     */
    private Pattern pattern;

    /**
     * 是否需要校验格式
     */
    private boolean needValidPattern;

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if(StringUtils.isBlank(value)){
            return false;
        }
        if(value.length() > length){
            return false;
        }

        if(needValidPattern){
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }

        return true;
    }

    @Override
    public void initialize(LengthAndPatternMatch constraintAnnotation) {

        length = constraintAnnotation.length();
        if(length == -1){
            length = Integer.MAX_VALUE;
        }
        LengthAndPatternMatch.CharType charType = constraintAnnotation.charType();
        needValidPattern = !charType.equals(LengthAndPatternMatch.CharType.ALLEPX);
        if(needValidPattern){
            pattern = Pattern.compile(charType.pattern());
        }
    }

}
