package com.gt.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonPropertyOrder({"name", "age", "sex", "birth_date"})
public class Son {

    private String name;

    private int age;

    // 序列化為birth_date
    @JsonProperty(value = "birth_date")
    // 格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthDate;

    // 不參與序列化與反序列化
    @JsonIgnore
    private int height;

    private int sex;
}
