package nkong.redis;

import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.cmd.Command;
import com.moilioncircle.redis.replicator.event.Event;
import com.moilioncircle.redis.replicator.event.EventListener;
import com.moilioncircle.redis.replicator.event.PostRdbSyncEvent;
import com.moilioncircle.redis.replicator.io.RawByteListener;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/1/9 17:03
 * 备份远程redis的实时命令
 */
public class CommandBackupExample {
    public static void main(String[] args) throws Exception {
        final OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("/path/to/appendonly.aof")));
        final RawByteListener rawByteListener = new RawByteListener() {
            @Override
            public void handle(byte... rawBytes) {
                try {
                    out.write(rawBytes);
                } catch (IOException ignore) {
                }
            }
        };

        //save 1000 records commands
        Replicator replicator = new RedisReplicator("redis://127.0.0.1:6379");
        final AtomicInteger acc = new AtomicInteger(0);
        replicator.addEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {
                if (event instanceof PostRdbSyncEvent) {
                    replicator.addRawByteListener(rawByteListener);
                }
                if (event instanceof Command) {
                    if (acc.incrementAndGet() == 1000) {
                        try {
                            out.close();
                            replicator.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        replicator.open();

        //check aof file
        replicator = new RedisReplicator("redis:///path/to/appendonly.aof");
        replicator.addEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {
                System.out.println(event);
            }
        });
        replicator.open();
    }

}
