package com.skyler.cobweb.utils;

import java.math.BigDecimal;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * <p>
 * NB.
 * </p>
 * Created by skyler on 2019-07-29 at 14:44
 */
public class BigDecimalUtil {

    /**
     * 乘法
     *
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal multiply(BigDecimal b1, BigDecimal b2) {
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 求比例，结果保留两位小数，舍入计算。<p>
     * 公式
     * <pre>
     *     sum1/sum2
     * </pre>
     *
     * @param sum1 分子
     * @param sum2 分母
     * @return 结果保留两位小数，舍入计算
     */
    public static BigDecimal rate(BigDecimal sum1, BigDecimal sum2) {
        return sum1.divide(sum2, 2, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 是否等于0/0.0/0.00
     *
     * @param b1
     * @return
     */
    public static boolean equalZero(BigDecimal b1) {
        return b1.compareTo(new BigDecimal(0)) == 0
                || b1.compareTo(new BigDecimal(0.0)) == 0
                || b1.compareTo(new BigDecimal(0.00)) == 0;
    }

    public static boolean equal(BigDecimal b1, BigDecimal b2) {
        b1.setScale(2,BigDecimal.ROUND_HALF_UP);
        return b1.compareTo(b2) == 0;
    }
}
