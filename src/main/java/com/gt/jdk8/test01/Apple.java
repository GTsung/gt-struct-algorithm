package com.gt.jdk8.test01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author GTsung
 * @date 2022/4/4
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Apple {

    private Integer weight;

    private String colour;
}
