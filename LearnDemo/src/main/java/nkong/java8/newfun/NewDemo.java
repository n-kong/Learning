package nkong.java8.newfun;

import java.util.function.BiFunction;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/9/6 23:20
 */
public class NewDemo {

    public NewDemo() {};

    public NewDemo(String str) {
        System.out.println(str);
    }

    public static String startWith(String str, String str2) {
        return String.valueOf(str.charAt(0)) + str2;
    }

    public String endWith(String str) {
        return String.valueOf(str.charAt(str.length() - 1));
    }

    public static void main(String[] args) {
        BiFunction<String, String, String> startWith = NewDemo::startWith;
        System.out.println(startWith.apply("qwe", "asd"));
//        String que = startWith.apply("que");
//        String qwe = startWith.convert("qwe");
//        System.out.println(que);
    }

}

@FunctionalInterface
interface IConvert<F, T, X> {
    T convert(F from, X to);
}
