package com.skyler.cobweb.agent.javassist;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020/3/18 at 7:46 下午
 */
public class Storage {

    private static Map<String, String> map;

    public static Map<String, String> map(){
        if(map == null) {
            map = new HashMap<>();
        }
        return map;
    }

}
