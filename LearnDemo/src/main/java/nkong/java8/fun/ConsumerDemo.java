package nkong.java8.fun;

import java.util.function.Consumer;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/9/5 22:51
 */
public class ConsumerDemo {

    public static void main(String[] args) {
        Consumer<Integer> consumer = x -> {
            int a = x + 2;
            System.out.println(a);// 12
            System.out.println(a + "_");// 12_
        };
        consumer.accept(10);
    }
}
