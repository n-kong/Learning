package nkong.java8.file;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/9/7 12:15
 */
public class FileDemo {

    public static void main(String[] args) {
        List list = getList(10);
        Stream stream = list.stream();

        Stream stream1 = stream.filter(n -> (Integer) n > 5);
        Stream stream2 = stream.filter(n -> (Integer) n < 5);

        System.out.println(stream1.collect(Collectors.toList()).toString());
        System.out.println(stream2.collect(Collectors.toList()).toString());
    }

    private static List getList(int size) {
        ArrayList<Integer> list = Lists.newArrayList();
        for (int n = 0; n < size; n++) {
            list.add(n);
        }
        return list;
    }


    @Test
    public void isExist() {
        Path path = Paths.get("src/main/resources/log4j.properties");
        boolean isExist = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println(isExist);
        System.out.println(path.toAbsolutePath());
    }

    /**
     * 递归遍历目录
     */
    @Test
    public void walk() {
        Path start = Paths.get("src/main/java/nkong/java8");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.walk(start, maxDepth)) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> path.endsWith(".java"))
                    .sorted()
                    .collect(Collectors.joining("; "));
            System.out.println("walk(): " + joined);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 函数式读取文件
     */
    @Test
    public void streamReader() {
        Path path = Paths.get("D:\\workspace\\Learning\\file\\flow\\flow.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            long countPrints = reader
                    .lines()
                    .filter(line -> line.contains("78"))
                    .count();
            System.out.println(countPrints);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 以buffer方式读取文件内容
     */
    @Test
    public void bufferReader() {
        Path path = Paths.get("D:\\workspace\\Learning\\file\\flow\\flow.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            System.out.println(reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取文件，一行一行读取
     */
    @Test
    public void readLine() {
        try (Stream<String> stream = Files.lines(Paths.get("D:\\workspace\\Learning\\file\\flow\\flow.txt"))) {
            stream.filter(line -> line.contains("78"))
                    .map(String::trim)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取文件，一次加载全部内容到内存
     */
    @Test
    public void readFile() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("D:\\workspace\\Learning\\file\\flow\\flow.txt"));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 递归遍历目录
     */
    @Test
    public void findFile() {
        Path start = Paths.get("src/main/java/nkong/java8");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).endsWith(".java"))) {
            String joined = stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
            System.out.println("Found: " + joined);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 采用Supplier可实现重复使用某stream流
     */
    @Test
    public void stream() {
        String[] array = {"a", "b", "c", "d", "e"};
        Supplier<Stream<String>> streamSupplier = () -> Stream.of(array);
        //get new stream
        streamSupplier.get().forEach(System.out::println);
        //get another new stream
        long count = streamSupplier.get().filter("b"::equals).count();
        System.out.println(count);
    }

    /**
     * Stream 不能被重用，一旦它被使用或使用，流将被关闭。
     */
    @Test
    public void closeStreamError() {
        String[] array = {"a", "b", "c", "d", "e"};
        Stream<String> stream = Arrays.stream(array);
        // loop a stream
        stream.forEach(System.out::println);
        // 拒绝过滤，抛出 throws IllegalStateException
        long count = stream.filter("b"::equals).count();
        System.out.println(count);
    }

    /**
     * 遍历目录下的文件，并拼接
     */
    @Test
    public void listFile() {
        try (Stream<Path> stream = Files.list(Paths.get("src/main/java/nkong/java8"))) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .sorted()
                    .collect(Collectors.joining("; "));
            System.out.println("List: " + joined);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
