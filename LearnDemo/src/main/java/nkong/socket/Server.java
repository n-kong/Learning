package nkong.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/8/28 16:17
 * 伪异步IO实现socket通信
 */
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) throws IOException {

        logger.info("===服务端启动===");
        ServerSocket socket = new ServerSocket(9999);
        ExecutorService executorService = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        while (true) {
            Socket s = socket.accept();
            logger.info("连接客户端，客户端地址：{}", s.getInetAddress().getHostAddress());
            executorService.execute(() -> {
                InputStream inputStream = null;
                try {
                    inputStream = s.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String msg;
                    while ((msg = bufferedReader.readLine()) != null) {
                        logger.info("服务端接收到：{}", msg);
                    }
                } catch (IOException e) {
                    logger.warn("客户端：[{}]断开连接...", s.getInetAddress().getHostAddress());
                }
            });
        }
    }
}
