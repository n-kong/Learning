package com.nkong.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/24 22:27
 */
public class NNClient {

    public static void main(String[] args) throws IOException {
        RPCProcotol client = RPC.getProxy(RPCProcotol.class, RPCProcotol.versionID,
                new InetSocketAddress("localhost", 8888), new Configuration());
        System.out.println("客户端开始工作");
        client.mkdir("/input");
    }


}
