package com.skyler.cobweb.agent.javassist;

import javassist.CtClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020/3/18 at 5:20 下午
 */
public class TargetAnnotations {

    public static final String FEIGN_CLIENT = "org.springframework.cloud.openfeign.FeignClient";
    public static final String REQUEST_MAPPING = "org.springframework.web.bind.annotation.RequestMapping";

    private static List<String> targetAnnotations;

    static {
        targetAnnotations = new ArrayList<>();
        targetAnnotations.add("org.springframework.web.bind.annotation.RestController");
        targetAnnotations.add("org.springframework.stereotype.Service");
        targetAnnotations.add("org.springframework.stereotype.Component");
    }

    public static List<String> getTargetAnnotations() {
        return targetAnnotations;
    }

    public static boolean hasTargetAnnotation(CtClass cl) {
        for (String targetAnnotation : targetAnnotations) {
            if(cl.hasAnnotation(targetAnnotation)) {
                return true;
            }
        }
        return false;
    }
}
