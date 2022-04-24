package nkong.java8.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/9/7 16:13
 */
public class Base64Demo {


    public static void main(String[] args) throws UnsupportedEncodingException {
        // 编码
        String asB64 = Base64.getEncoder().encodeToString("some string".getBytes("utf-8"));
        System.out.println(asB64); // 输出为: c29tZSBzdHJpbmc=
        // 解码
        byte[] asBytes = Base64.getDecoder().decode("c29tZSBzdHJpbmc=");
        System.out.println(new String(asBytes, "utf-8")); // 输出为: some string

    }

}
