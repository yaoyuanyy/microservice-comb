package com.skyler.cobweb.model;

import lombok.Data;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.Nullable;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2019-12-24 at 14:27
 */
public class FeignMethodInfoHolder {

    private static final ThreadLocal<FeignMethodInfo> feignHolder = new NamedThreadLocal<>("FeignMethodInfo");

    public static void set(@Nullable FeignMethodInfo feignMethodInfo){
        feignHolder.set(feignMethodInfo);
    }

    public static FeignMethodInfo get(){
        return feignHolder.get();
    }

    public static void remove(){
        feignHolder.remove();
    }

    @Data
    protected static class FeignMethodInfo {
        private String toApplication;
        private String toUrl;
        private String fromUrl;
        private String fromClass;
    }
}
