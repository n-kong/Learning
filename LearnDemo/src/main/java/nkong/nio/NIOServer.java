package nkong.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/8/26 22:29
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        // 获取选择器
        Selector selector = Selector.open();
        // 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 切换为非阻塞模式
        ssChannel.configureBlocking(false);
        // 将通道注册到选择器上，并且开始指定监听接收事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        ServerSocket serverSocket = ssChannel.socket();
        // 绑定连接地址
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        serverSocket.bind(address);
        // 轮询已经就绪好的事件
        while (selector.select() > 0) {
            // 获取选择器中的所有注册的通道中已经就绪好的事件
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            // 遍历准备好的事件
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                // 判断具体是什么事件准备就绪
                if (key.isAcceptable()) {
                    ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel();
                    // 服务器会为每个新连接创建一个 SocketChannel
                    SocketChannel sChannel = ssChannel1.accept();
                    // 切换非阻塞模式
                    sChannel.configureBlocking(false);
                    // 将通道注册到选择器上，这个新连接主要用于从客户端读取数据
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 获取通道
                    SocketChannel sChannel = (SocketChannel) key.channel();
                    System.out.println(readDataFromSocketChannel(sChannel));
                    sChannel.close();
                }
                // 取消选择键
                keyIterator.remove();
            }
        }
    }

    private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {

        // 缓冲区初始容量大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder data = new StringBuilder();

        while (true) {
            // 清空缓冲区，只是把起始位置置为0，buffer中的数据并没有清除，等下次写入时会覆盖掉
            buffer.clear();
            int n = sChannel.read(buffer);
            if (n == -1) {
                break;
            }
            // 切换为可读模式
            buffer.flip();
            int limit = buffer.limit();
            char[] dst = new char[limit];
            for (int i = 0; i < limit; i++) {
                dst[i] = (char) buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }
}

