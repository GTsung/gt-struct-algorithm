package com.gt.nio.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class FileDemo {

    public static void main(String[] args) {
        // 获取路径
        Path path = Paths.get("01.txt");
        // 相对路径
//        Path path1 = Paths.get("d://ss", "s.txt");

        // 标准
        Path path2 = path.normalize();
        System.out.println(path2.toString());

        // 创建目录
        Path p = Paths.get("d:\\eggs");
        try {
            Path newDir = Files.createDirectory(p);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 将一个目录下的文件拷贝到另一个目录下
        // 如果d:\\bb\\b.txt存在则会报异常
        Path p2 = Paths.get("d:\\aa\\a.txt");
        Path p3 = Paths.get("d:\\bb\\b.txt");
        try {
            Files.copy(p2, p3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 覆盖
        try {
            Files.copy(p2, p3, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // move
        try {
            Files.move(p2, p3, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // delete
        try {
            Files.delete(p2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 遍历File目录树
        Path rootPath = Paths.get("d:\\a");
        String fileToFind = File.separator + "01.sql";
        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString  = file.toAbsolutePath().toString();
                    System.out.println(fileString);

                    if (fileString.endsWith(fileToFind)) {
                        System.out.println("file found at path:" + file.toAbsolutePath());
                        // 结束遍历
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
