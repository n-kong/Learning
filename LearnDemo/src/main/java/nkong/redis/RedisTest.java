package nkong.redis;

import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.cmd.impl.SetCommand;
import com.moilioncircle.redis.replicator.event.Event;
import com.moilioncircle.redis.replicator.event.EventListener;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyStringValueList;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyStringValueString;

import java.io.IOException;

public class RedisTest {

    public static void main(String[] args) throws Exception {

        RedisReplicator redisReplicator = new RedisReplicator("redis://127.0.0.1:6379?authPassword=nkong");
        redisReplicator.addEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {


                if (event instanceof KeyStringValueString) {
                    KeyStringValueString kv = (KeyStringValueString) event;
                    byte[] key = kv.getKey();
                    System.out.println("out value -- " + new String(kv.getValue()));
                } else if (event instanceof KeyStringValueList) {
                    KeyStringValueList kl  = (KeyStringValueList) event;
                    byte[] key = kl.getKey();
                    System.out.println("out list key --" + new String(key));
                    for (byte[] bytes : kl.getValue()) {
                        System.out.println("out list value --" + new String(bytes));
                    }
                }

                System.out.println("time" + event);
            }
        });
        redisReplicator.open();
    }


}
