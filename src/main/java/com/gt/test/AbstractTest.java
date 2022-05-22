package com.gt.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * @author GTsung
 * @date 2022/5/22
 */
public class AbstractTest {


    public static abstract class AbstractMiss<T> {

        private static final String REF_NBR = "WOO";

        private Class clazz;

        public AbstractMiss() {
            Type[] clazzs = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
            clazz = (Class) clazzs[0];
        }

        protected void handle() {
            if (match()) {
                newProcess();
            } else {
                System.out.println("++++++++++++++++++++++++++++");
            }
        }

        protected abstract void newProcess() ;

        private boolean match() {
            String fieldVal = null;
            try {
                Field refNbr = clazz.getDeclaredField("refNbr");
                refNbr.setAccessible(true);
                fieldVal = (String) refNbr.get(clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return REF_NBR.equalsIgnoreCase(fieldVal);
        }
    }

    public static class MissY extends AbstractMiss<CcsLoan> {
        @Override
        protected void newProcess() {
            System.out.println("=============================");
        }
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CcsLoan {
        private Long id;
        private BigDecimal amount;
        private String refNbr;
    }

    public static void main(String[] args) {
        MissY missY = new MissY();
        missY.handle();
    }
}
