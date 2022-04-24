package com.nkong.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/24 22:23
 */
public class NNServer implements RPCProcotol{

    public static void main(String[] args) throws IOException {
        RPC.Server server = new RPC.Builder(new Configuration())
                .setBindAddress("localhost")
                .setPort(8888)
                .setProtocol(RPCProcotol.class)
                .setInstance(new NNServer())
                .build();
        System.out.println("服务端开始工作...");
        server.start();
    }

    @Override
    public void mkdir(String path) {
        System.out.println("服务端接收到客户端请求：" + path);
    }
}
