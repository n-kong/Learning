package nkong.java8;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import nkong.classLoder.SalaryCaler;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * java8新特性
 *
 * @author nkong
 * @version 1.0
 * @date 2021/8/21 17:45
 */
public class Lambda {

    public static void main(String[] args) {

        // forEach
        List<String> lists = Lists.newArrayList("tab1", "tab2", "tab3", "tab2");
//        lists.forEach(n -> System.out.println(n + "*"));
//        lists.forEach(System.out::println);

        // 及早求值法
        List<String> tab1 = lists.stream().filter(f -> f.equalsIgnoreCase("tab1")).collect(Collectors.toList());
//        tab1.forEach(System.out::println);

        // 顺序处理流
        Set<String> sets = lists.stream().collect(Collectors.toSet());
//        sets.forEach(System.out::println);

        // 并行处理流：对数组切分，并行处理之后再汇总
        List<String> collect = lists.stream().parallel().collect(Collectors.toList());
//        collect.forEach(System.out::println);

        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
        int pro = 1;
        // lambda只能引用 final 或 final 局部变量，就是说不能在lambda内部修改定义在域外的变量。
//        integers.forEach(n ->  System.out.println(pro++)); // 报错
//        integers.forEach(n ->  System.out.println(pro + n));

        // 基本类型数组转包装类型集合
        int[] ints = IntStream.range(0, 100).filter(p -> p % 2 == 0).toArray();
        List<Integer> collect2 = Arrays.stream(ints).boxed().collect(Collectors.toList());
//        collect2.forEach(System.out::println);


        // 数组转集合
        String[] s = {"aa", "bb"};
        List<String> collect1 = Stream.of(s).collect(Collectors.toList());
//        collect1.forEach(System.out::println);

        // 线程
//        new Thread(() -> System.out.println("lambda demo...")).start();
        new Thread(() -> {
            List<String> list = Lists.newArrayList();
        }).start();

        Supplier<SalaryCaler> supplier = SalaryCaler::new;
//        System.out.println(supplier.get().cal(8.0));

        // reduce
        List<Integer> costBeforeTax = Arrays.asList(100, 200);
        double bill = costBeforeTax.stream().reduce(0, (a, b) -> a + b);
//        System.out.println("Total : " + bill);

        // map
        List<Integer> integerStream = costBeforeTax.stream().map(n -> n * 2).collect(Collectors.toList());
//        integerStream.forEach(System.out::println);

        // Collector
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
//        System.out.println(G7Countries);

        // match
        boolean j = G7.stream().anyMatch(n -> n.startsWith("A")); // false
        boolean j1 = G7.stream().allMatch(n -> n.startsWith("J")); // false
        boolean u = G7.stream().noneMatch(n -> n.startsWith("B")); // true
//        System.out.println(u);

        // flatMap
        List<Integer> result= Stream.of(Arrays.asList(1,3),Arrays.asList(5,6)).flatMap(a->a.stream()).collect(Collectors.toList());
//        result.forEach(System.out::println);

        // distinct去重
        List<Integer> integers1 = Arrays.asList(100, 200, 300, 200, 100);
        List<Integer> collect3 = integers1.stream().distinct().collect(Collectors.toList());
//        collect3.forEach(System.out::println);

        // count计数
        long count = integers1.stream().filter(n -> n > 150).count();
//        System.out.println(count);

//        System.out.println(Base64.getEncoder().encodeToString("aa".getBytes()));


        // compute
        String str = "hello java, i am vary happy! nice to meet you";
        HashMap<Character, Integer> result2 = new HashMap<>(32);
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            result2.compute(curChar, (k, v) -> {
                if (v == null) {
                    v = 1;
                } else {
                    v += 1;
                }
                return v;
            });
        }
        result2.forEach((k, v) -> System.out.println("k:" + k + ", v:" + v));



    }


}
