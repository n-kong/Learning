package nkong.socket.bio;

import com.google.common.collect.Lists;
import nkong.socket.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/8/29 21:46
 *
 * BIO模式下的端口转发思想
 *     1、注册端口
 *     2、接收客户端的socket连接，交给一个独立的线程处理
 *     3、把当前连接的socket存入一个在线的集合中
 *     4、接收客户端的消息，然后推送给所有的在线socket
 *
 */
public class BIOServer {
    private static final Logger logger = LoggerFactory.getLogger(BIOServer.class);
    private static List<Socket> onLineSockets = Lists.newArrayList();
    public static void main(String[] args) throws IOException {
        logger.info("===服务端启动===");
        ServerSocket socket = new ServerSocket(9999);
        ExecutorService executorService = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        while (true) {
            Socket s = socket.accept();
            logger.info("连接客户端，客户端地址：{}", s.getInetAddress().getHostAddress());
            onLineSockets.add(s);
            executorService.execute(() -> {
                InputStream inputStream = null;
                try {
                    inputStream = s.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String msg;
                    while ((msg = bufferedReader.readLine()) != null) {
                        sendMsgToAllClient(s, msg);
                        logger.info("服务端接收到：{}", msg);
                    }
                } catch (IOException e) {
                    // 客户端下线，在线集合移除
                    onLineSockets.remove(s);
                    logger.warn("客户端：[{}]断开连接...", s.getInetAddress().getHostAddress());
                }
            });
        }
    }

    private static void sendMsgToAllClient(Socket socket, String msg) {
        onLineSockets.stream().filter(x -> x != socket).forEach(n -> {
            PrintStream printStream = null;
            try {
                printStream = new PrintStream(n.getOutputStream());
                printStream.println(msg);
                printStream.flush();
                logger.info("向客户端[{}]发送消息：[{}]成功！", n.getInetAddress().getHostAddress(), msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
