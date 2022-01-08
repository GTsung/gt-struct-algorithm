package com.gt.pattern.struct.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取享元对象的工厂
 * @author GTsung
 * @date 2022/1/8
 */
public class BoxFactory {

    private Map<String, AbstractBox> map ;

    private static BoxFactory boxFactory = new BoxFactory();

    private BoxFactory() {
        map = new HashMap<>();
        map.put("L", new LBox());
        map.put("Z", new ZBox());
        map.put("T", new TBox());
    }

    public static BoxFactory getInstance() {
        return boxFactory;
    }

    public AbstractBox getBoxByShape(String shape) {
        return map.get(shape);
    }


}
