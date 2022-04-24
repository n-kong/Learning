package nkong.redis;

import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.event.Event;
import com.moilioncircle.redis.replicator.event.EventListener;
import com.moilioncircle.redis.replicator.event.PostRdbSyncEvent;
import com.moilioncircle.redis.replicator.event.PreRdbSyncEvent;
import com.moilioncircle.redis.replicator.io.RawByteListener;
import com.moilioncircle.redis.replicator.rdb.skip.SkipRdbVisitor;

import java.io.*;

/**
 * 备份远程redis的rdb文件
 */
public class RdbBackupExample {

    public static void main(String[] args) throws Exception {

        final OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("file/dump.rdb")));
        final RawByteListener rawByteListener = new RawByteListener() {
            @Override
            public void handle(byte... rawBytes) {
                try {
                    out.write(rawBytes);
                } catch (IOException ignore) {
                }
            }
        };

        //save rdb from remote server
        Replicator replicator = new RedisReplicator("redis://127.0.0.1:6379");
        replicator.setRdbVisitor(new SkipRdbVisitor(replicator));
        replicator.addEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {
                if (event instanceof PreRdbSyncEvent) {
                    replicator.addRawByteListener(rawByteListener);
                }

                if (event instanceof PostRdbSyncEvent) {
                    replicator.removeRawByteListener(rawByteListener);
                    try {
                        out.close();
                        replicator.close();
                    } catch (IOException ignore) {
                    }
                }
            }
        });
        replicator.open();

        //check rdb file
        replicator = new RedisReplicator("redis:///D:/soft/Redis/dump.rdb");
        replicator.addEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {
                System.out.println(event);
            }
        });
        replicator.open();
    }

}
