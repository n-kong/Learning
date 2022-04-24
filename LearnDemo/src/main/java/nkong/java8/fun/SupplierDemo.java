package nkong.java8.fun;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/9/5 22:15
 */
public class SupplierDemo {

    public static void main(String[] args) {
        Supplier<LocalDateTime> time = LocalDateTime::now;
        System.out.println(time.get());
        fun(() -> {
            int[] arr = {11,22,56,34,67,23,77};
            Arrays.sort(arr);
            return arr[arr.length - 1];
        });
    }

    public static void fun(Supplier<Integer> supplier) {
        Integer max = supplier.get();
        System.out.println("max = " + max);
    }
}
