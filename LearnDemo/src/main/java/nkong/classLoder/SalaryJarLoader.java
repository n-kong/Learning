package nkong.classLoder;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureClassLoader;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/7/22 22:34
 */
public class SalaryJarLoader extends SecureClassLoader {

    private String jarPath;

    public SalaryJarLoader(String jarPath) {
        this.jarPath = jarPath;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith("com.nkong")) {
            return this.findClass(name);
        }
        return super.loadClass(name, resolve);


//        synchronized (getClassLoadingLock(name)) {
//            // First, check if the class has already been loaded
//            Class<?> c = findLoadedClass(name);
//            if (c == null) {
//                c = this.findClass(name);
//                if (c == null) {
//                    c = super.loadClass(name, resolve);
//                }
//            }
//            return c;
//        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filePath = name.replace(".", "/").concat(".class");
        URL url = null;
        InputStream inputStream = null;
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer();
        byte[] b = null;
        int code;
        try {
            url = new URL("jar:file:\\" + this.jarPath + "!/" + filePath);
            System.out.println(url.getPath());
            inputStream = url.openStream();
            while ((code = inputStream.read()) != -1) {
                byteArrayBuffer.write(code);
            }
            b = byteArrayBuffer.toByteArray();
        } catch (Exception e) {

        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return this.defineClass(name, b, 0, b.length);
    }
}
