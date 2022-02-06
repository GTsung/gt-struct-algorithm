package com.gt.base.reflect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class MyClass {

    protected List<String> list = new ArrayList<>();

    public Map<Integer, Long> map = new HashMap<>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> stringList) {
        list = stringList;
    }
}
