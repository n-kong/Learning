package nkong.classLoder;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.security.SecureClassLoader;
import java.util.concurrent.ExecutionException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/7/22 22:13
 */
public class SalaryClassLoader extends SecureClassLoader {

    // 文件路径
    private String classPath;

    public SalaryClassLoader(String classPath) {
        this.classPath = classPath;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filePath = this.classPath + name.replace(".", "\\").concat(".myclass");
        System.out.println(filePath);
        FileInputStream inputStream;
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer();
        byte[] b = null;
        int code;
        try {
            inputStream = new FileInputStream(new File(filePath));
            while ((code = inputStream.read()) != -1) {
                byteArrayBuffer.write(code);
            }
            b = byteArrayBuffer.toByteArray();
        } catch (Exception e) {

        }
        return this.defineClass(name, b, 0, b.length);
    }
}
