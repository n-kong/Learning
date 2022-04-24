package nkong.dealfile;

import org.apache.commons.lang3.time.DateUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/1/16 15:16
 */
public class ZipUtil {

    public static void main(String[] args) throws IOException {

        System.out.println();
        File file1 = new File("file/file.zip");
        System.out.println(file1.length());
        FileInputStream in = new FileInputStream("file/file.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in, Charset.forName("GBK"));
        System.out.println(in.available());

//        ZipEntry zipEntry = null;
//
//        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
//            if (zipEntry.getName().endsWith(".mp3")) {
//                File file = new File("file_out" + File.separator + zipEntry.getName());
//                FileOutputStream outputStream = new FileOutputStream(file);
//                int len = 0;
//                byte[] bytes = new byte[4096];
//                while ((len = zipInputStream.read(bytes)) > 0) {
//                    outputStream.write(bytes, 0, len);
//                }
//                outputStream.close();
//                zipInputStream.closeEntry();
//            }
//        }



    }

}
