package com.gt.base.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author GTsung
 * @date 2022/3/28
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(12.928);
        BigDecimal bigDecimal2 = new BigDecimal(-12.928);
        // 12.93
        System.out.println(print(bigDecimal, RoundingMode.HALF_UP));
        // 12.93
        System.out.println(print(bigDecimal, RoundingMode.CEILING));
        // 12.92
        System.out.println(print(bigDecimal, RoundingMode.DOWN));
        // 12.93
        System.out.println(print(bigDecimal, RoundingMode.HALF_DOWN));
        // 12.92
        System.out.println(print(bigDecimal, RoundingMode.FLOOR));
        // 12.93
        System.out.println(print(bigDecimal, RoundingMode.HALF_EVEN));
        // 12.93
        System.out.println(print(bigDecimal, RoundingMode.UP));

        // -12.93
        System.out.println(print(bigDecimal2, RoundingMode.HALF_UP));
        // -12.92
        System.out.println(print(bigDecimal2, RoundingMode.CEILING));
        // -12.92
        System.out.println(print(bigDecimal2, RoundingMode.DOWN));
        // -12.93
        System.out.println(print(bigDecimal2, RoundingMode.HALF_DOWN));
        // -12.93
        System.out.println(print(bigDecimal2, RoundingMode.FLOOR));
        // -12.93
        System.out.println(print(bigDecimal2, RoundingMode.HALF_EVEN));
        // -12.93
        System.out.println(print(bigDecimal2, RoundingMode.UP));
    }

    private static BigDecimal print(BigDecimal num, RoundingMode roundingMode) {
        return num.setScale(2, roundingMode);
    }
}
