package com.skyler.cobweb.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * <p>
 * NB.
 * </p>
 * Created by skyler on 2019-08-12 at 16:35
 */
public class StringUtil {

    /**
     * 获取最大的
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String max(String s1, String s2) {
        if (null != s1 && null != s2) {
            int i = s1.compareTo(s2);
            if (i >= 0) {
                return s1;
            }
            return s2;
        }

        if (null != s1) {
            return s1;
        }
        if (null != s2) {
            return s2;
        }

        return null;
    }

    /**
     * 获取最小的
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String min(String s1, String s2) {
        if (null != s1 && null != s2) {
            int i = s1.compareTo(s2);
            if (i <= 0) {
                return s1;
            }
            return s2;
        }

        if (null != s1) {
            return s1;
        }
        if (null != s2) {
            return s2;
        }

        return null;
    }

    /**
     * 获取入参的值，如果为null or ""， 则返回""；否则入参==出参
     *
     * @param createName
     * @return
     */
    public static String getValue(String createName) {
        return getValueOrDefault(createName, "");
    }

    /**
     * 如果入参是null or ""，出参为默认值；否则为否则入参==出参
     * @param createName
     * @param defaultValue
     * @return
     */
    public static String getValueOrDefault(String createName, String defaultValue) {
        return StringUtils.isBlank(createName) ? defaultValue : createName;
    }


    public static void main(String[] args) {
        System.out.println(max("s", null));
    }

}
