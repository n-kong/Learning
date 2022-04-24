package nkong.redis;

import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.event.Event;
import com.moilioncircle.redis.replicator.event.EventListener;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyStringValueList;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyStringValueString;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/1/9 17:57
 */
public class BroadcastEventExample {
    public static void main(String[] args) throws Exception {
        Replicator replicator = new RedisReplicator("redis://127.0.0.1:6379");

        //broadcast rdb event
        replicator.addEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {
//                if (event instanceof KeyStringValueString) {
//                    KeyStringValueString kv = (KeyStringValueString) event;
//                    byte[] key = kv.getKey();
//                    System.out.println("v1 out key -- " + new String(kv.getKey()));
//                    System.out.println("v1 out value -- " + new String(kv.getValue()));
//                } else if (event instanceof KeyStringValueList) {
//                    KeyStringValueList kl  = (KeyStringValueList) event;
//                    byte[] key = kl.getKey();
//                    System.out.println("v1 out list key --" + new String(key));
//                    for (byte[] bytes : kl.getValue()) {
//                        System.out.println("v1 out list value --" + new String(bytes));
//                    }
//                }
                System.out.println("broadcast event channel 1 " + event);
            }
        });

        replicator.open();
    }
}
