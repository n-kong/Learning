package nkong.java8.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/9/6 15:18
 */
public class SortTest {

    public static void main(String[] args) {
        List<Integer> lists = Lists.newArrayList(23, 45, 12, 67);
        Collections.sort(lists, (Integer s1, Integer s2) -> {
            return s2 - s1;
        });

//        lists.forEach(System.out::println);

        test(msg ->{
            return Integer.parseInt(msg);
        },msg2->{
            return msg2 * 10;
        });

        test(msg ->{
            return Integer.parseInt(msg) * 2;
        });

//        printMax(SortTest::getTotal2);

        Supplier<String> print = SortTest::print;
        System.out.println(print.get());

        Date now = new Date();
        Supplier<Long> supplier1 = now::getTime;
    }

    public static String print() {
        System.out.println("test new");
        return "000";
    }

    @Test
    public void test03(){
        LocalDateTime now = LocalDateTime.now();
        // 指定格式  使用系统默认的格式 2021-05-27T16:16:38.139
        DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // 将日期时间转换为字符串
        String format = now.format(isoLocalDateTime);
        System.out.println("format = " + format);

        // 通过 ofPattern 方法来指定特定的格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format1 = now.format(dateTimeFormatter);
        // 2021-05-27 16:16:38
        System.out.println("format1 = " + format1);

        // 将字符串解析为一个 日期时间类型
        LocalDateTime parse = LocalDateTime.parse("1997-05-06 22:45:16", dateTimeFormatter);
        // parse = 1997-05-06T22:45:16
        System.out.println("parse = " + parse);
    }

    @Test
    public void test02(){
        // 1.创建指定的日期
        LocalDate date1 = LocalDate.of(2021, 05, 06);
        System.out.println("date1 = "+date1);

        // 2.得到当前的日期
        LocalDate now = LocalDate.now();
        System.out.println("now = "+now.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        // 3.根据LocalDate对象获取对应的日期信息
        System.out.println("年：" + now.getYear());
        System.out.println("月：" + now.getMonth().getValue());
        System.out.println("日：" + now.getDayOfMonth());
        System.out.println("星期：" + now.getDayOfWeek().getValue());
    }

    @Test
    public void test01() throws Exception{
        // 1.设计不合理
        Date date = new Date(2021,05,05);
        System.out.println(date);

        // 2.时间格式化和解析操作是线程不安全的
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                // System.out.println(sdf.format(date));
                try {
                    System.out.println(sdf.parse("2021-05-06"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


    /**
     * 求数组中的所有元素的和
     * @param a
     */
    public static void getTotal2(int a[]){
        System.out.println("一号元素：" + a[0]);
    }

    /**
     * 求数组中的所有元素的和
     * @param a
     */
    public static void getTotal(int a[]){
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        System.out.println("数组之和：" + sum);
    }

    private static void printMax(Consumer<int[]> consumer){
        int[] a= {10,20,30,40,50,60};
        consumer.accept(a);
    }

    public static void test(Function<String,Integer> function){
        Integer apply = function.apply("666");
        System.out.println("apply = " + apply);
    }

    public static void test(Function<String,Integer> f1, Function<Integer,Integer> f2){
        /*Integer i1 = f1.apply("666");
        Integer i2 = f2.apply(i1);*/
        Integer i2 = f1.andThen(f2).apply("666");
        System.out.println("i2:" + i2);

    }
}
