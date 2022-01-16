package com.gt.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author GTsung
 * @date 2022/1/16
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Dog extends DogFather implements Animal {

    private String name;

    private int age;

    private Date birthDate;

    public void drink() {
        System.out.println("喝水");
    }

    public void drink(String water) {
        System.out.println("在喝"+ water);
    }

    public void drink(String water, String cola) {
        System.out.println("在喝" + water + "," + cola);
    }

    @Override
    public void play() {
        System.out.println("play");
    }
}
