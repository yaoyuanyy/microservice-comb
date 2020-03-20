package com.skyler.cobweb.agent.javassist;

import javassist.CtClass;
import javassist.CtMethod;

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
    private static List<String> targetRequestAnnotations;

    static {
        targetAnnotations = new ArrayList<>();
        targetAnnotations.add("org.springframework.web.bind.annotation.RestController");
        targetAnnotations.add("org.springframework.stereotype.Service");
        targetAnnotations.add("org.springframework.stereotype.Component");

        targetRequestAnnotations = new ArrayList<>();
        targetRequestAnnotations.add("org.springframework.web.bind.annotation.GetMapping");
        targetRequestAnnotations.add("org.springframework.web.bind.annotation.PostMapping");
        targetRequestAnnotations.add("org.springframework.web.bind.annotation.RequestMapping");
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

    public static List<String> getTargetRequestAnnotations() {
        return targetRequestAnnotations;
    }

    public static boolean hasTargetRequestAnnotation(CtMethod ctMethod) {
        for (String targetRequestAnnotation : targetRequestAnnotations) {
            if(ctMethod.hasAnnotation(targetRequestAnnotation)) {
                return true;
            }
        }
        return false;
    }
}
