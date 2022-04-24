package com.nkong.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/8/26 20:34
 */
public class IOUtils {

    public static void main(String[] args) throws IOException {

        fastCopy("file/flow/flow.txt", "file/wc/flow.txt");

    }


    /**
     * nio 实现复制文件
     * @param src
     * @param dist
     * @throws IOException
     */
    public static void fastCopy(String src, String dist) throws IOException {
        /* 获得源文件的输入字节流 */
        FileInputStream fin = new FileInputStream(src);
        /* 获取输入字节流的文件通道 */
        FileChannel fcin = fin.getChannel();
        /* 获取目标文件的输出字节流 */
        FileOutputStream fout = new FileOutputStream(dist);
        /* 获取输出字节流的通道 */
        FileChannel fcout = fout.getChannel();
        /* 为缓冲区分配 1024 个字节 */
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            /* 从输入通道中读取数据到缓冲区中 */
            int r = fcin.read(buffer);
            /* read() 返回 -1 表示 EOF */
            if (r == -1) {
                break;
            }
            /* 切换读写 */
            buffer.flip();
            /* 把缓冲区的内容写入输出文件中 */
            fcout.write(buffer);
            /* 清空缓冲区 */
            buffer.clear();
        }
        fcout.close();
        fout.close();
        fcin.close();
        fin.close();
    }


}
