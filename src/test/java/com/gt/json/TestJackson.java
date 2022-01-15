package com.gt.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Slf4j
public class TestJackson {

    @Test
    public void test01() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = Person.builder().name("adams").age(11).date(new Date()).build();
        String jsonStr = mapper.writeValueAsString(person);
        // {"name":"adams","age":11,"date":1642248901212,"height":0}
        log.debug("序列化person，{}", jsonStr);
        Person p = mapper.readValue(jsonStr, Person.class);
        // Person(name=adams, age=11, date=Sat Jan 15 20:15:01 CST 2022, height=0)
        log.debug("反序列化jsonStr，{}", p);
    }

    @Test
    public void test02() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 序列化時將對象的日期屬性序列化為時間戳格式設置成false
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 序列化時自定義時間格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 序列化時忽略值為null的屬性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 序列化時忽略值爲默認值的屬性
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
        // 反序列化時將json中的未知屬性省略
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Person p = Person.builder().date(new Date()).age(12).build();
        String jsonStr = mapper.writeValueAsString(p);
        // {"age":12,"date":"2022-01-15 20:23:11"}
        log.debug("序列化person,{}", jsonStr);

        String jsonString = "{\"age\":12,\"date\":\"2022-01-15 20:23:11\", \"son\": \"LiuXiao\"}";
        Person person = mapper.readValue(jsonString, Person.class);
        // Person(name=null, age=12, date=Sat Jan 15 20:23:11 CST 2022, height=0)
        log.debug("反序列化jsonString，{}", person);
    }

    @Test
    public void test03() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Son son = Son.builder().name("劉蕭").sex(1).age(12)
                .birthDate(new Date()).height(12).build();
        String jsonStr = mapper.writeValueAsString(son);
        // {"name":"劉蕭","age":12,"sex":1,"birth_date":"2022-01-15 12:32:29"}
        log.debug("序列化son:{}", jsonStr);
    }

    @Test
    public void test04() throws JsonProcessingException {
        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder().age(new Random().nextInt(10)).name("aa").date(new Date()).build());
        personList.add(Person.builder().age(new Random().nextInt(10)).name("bb").date(new Date()).build());
        personList.add(Person.builder().age(new Random().nextInt(10)).name("cc").date(new Date()).build());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(personList);
        // [{"name":"aa","age":1,"date":1642252807978,"height":0},{"name":"bb","age":6,"date":1642252807978,"height":0},
        // {"name":"cc","age":1,"date":1642252807978,"height":0}]
        log.debug("序列化list:{}", jsonStr);

        // 反序列化
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Person.class);
        List<Person> persons = mapper.readValue(jsonStr, type);
        persons.forEach(p -> {
            log.debug("person:{}", p);
        });

        // 反序列化2
        List<Person> persons2 = mapper.readValue(jsonStr, new TypeReference<List<Person>>() {
        });
        persons2.forEach(p -> log.debug("person:{}", p));
    }

    @Test
    public void test05() throws JsonProcessingException {
        Map<String, Person> map = new HashMap<>();
        map.put("adams", Person.builder().name("adams").age(12).date(new Date()).build());
        map.put("jesus", Person.builder().name("jesus").age(2).date(new Date()).build());
        map.put("god", Person.builder().name("god").age(11).date(new Date()).build());

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(map);
        // {"adams":{"name":"adams","age":12,"date":1642253328731,"height":0},
        // "jesus":{"name":"jesus","age":2,"date":1642253328731,"height":0},
        // "god":{"name":"god","age":11,"date":1642253328731,"height":0}}
        log.debug("序列化map:{}", jsonStr);

        // 反序列化
        MapType type = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Person.class);
        Map<String, Person> personMap = mapper.readValue(jsonStr, type);
        personMap.forEach((k, v) -> {
            log.debug("反序列化，{}-{}", k, v);
        });

        Map<String, Person> personMap1 = mapper.readValue(jsonStr, new TypeReference<Map<String, Person>>() {
        });
        personMap1.forEach((k, v) -> {
            log.debug("{}-{}", k, v);
        });
    }
}
