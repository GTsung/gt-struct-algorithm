package com.gt.json;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class Person {

    private String name;

    private Integer age;

    private Date date;

    private int height;
}
