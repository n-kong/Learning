package nkong.socket.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/8/29 21:47
 */
public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream outputStream = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("输入：");
            String msg = scanner.next();
            printStream.println(msg);
            printStream.flush();
        }
    }

}
