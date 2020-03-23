package com.skyler.cobweb.agent.javassist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020/3/23 at 5:11 下午
 */
public class PatternUtil {

    private static final String REGX = "[value|name]=\"(.*?)\"";

    public static String match(String regx, String str) {
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    public static String match(String str) {
        return match(REGX, str);
    }

    public static void main(String[] args) {
        String str = "org.springframework.cloud.openfeign.FeignClient(name=\"microservice-comb-server-b\", contextId=\"ProductFeignClient\")";
        String regx = "=\"(.*?)\"";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

}
