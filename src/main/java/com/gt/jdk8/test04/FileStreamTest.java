package com.gt.jdk8.test04;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author GTsung
 * @date 2022/4/4
 */
public class FileStreamTest {

    public static void main(String[] args) {
        long uniqueWords = 0;
        try (Stream<String> lines =
                     Files.lines(Paths.get("a.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (Exception e) {

        }
        System.out.println(uniqueWords);
    }
}
