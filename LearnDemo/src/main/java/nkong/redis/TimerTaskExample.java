package nkong.redis;

import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.event.Event;
import com.moilioncircle.redis.replicator.event.EventListener;
import com.moilioncircle.redis.replicator.event.PostRdbSyncEvent;
import com.moilioncircle.redis.replicator.event.PreRdbSyncEvent;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyStringValueList;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyValuePair;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/1/9 17:40
 */
public class TimerTaskExample {

    public static void main(String[] args) throws IOException {
        final Timer timer = new Timer("sync");
        timer.schedule(new Task(), 1000, 5000);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                timer.cancel();
            }
        });
        System.in.read();
    }

    private static class Task extends TimerTask {
        @Override
        public void run() {
            try {
                Replicator replicator = new RedisReplicator("redis://127.0.0.1:6379");
                replicator.addEventListener(new EventListener() {
                    @Override
                    public void onEvent(Replicator replicator, Event event) {
                        if (event instanceof PreRdbSyncEvent) {
                            System.out.println("data sync started");
                        }
                        if (event instanceof KeyValuePair<?, ?>) {
                            //shard kv.getKey to different thread so that speed up save process.
//                            save((KeyValuePair<?, ?>) event);
                        }
                        if (event instanceof KeyStringValueList){
                            KeyStringValueList keyStringValueList = (KeyStringValueList) event;
                            byte[] key = keyStringValueList.getKey();
                            List<byte[]> value = keyStringValueList.getValue();
                            System.out.println("key:" + new String(key));
                            for (byte[] bytes : value) {
                                System.out.println("value:" + new String(bytes));
                            }
                        }
                        if (event instanceof PostRdbSyncEvent) {
                            try {
                                replicator.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("data sync done");
                        }
                    }
                });

                replicator.open();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void save(KeyValuePair<?, ?> kv) {
//        System.out.println(kv);
        System.out.println(kv.getKey() + ":" + kv.getValue());
        //save kv to mysql or to anywhere.
    }

}
