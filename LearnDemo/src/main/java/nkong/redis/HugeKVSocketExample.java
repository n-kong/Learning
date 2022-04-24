package nkong.redis;

import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.cmd.Command;
import com.moilioncircle.redis.replicator.cmd.impl.PublishCommand;
import com.moilioncircle.redis.replicator.event.Event;
import com.moilioncircle.redis.replicator.event.EventListener;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyStringValueString;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyValuePair;
import com.moilioncircle.redis.replicator.rdb.iterable.ValueIterableEventListener;
import com.moilioncircle.redis.replicator.rdb.iterable.ValueIterableRdbVisitor;
import com.moilioncircle.redis.replicator.rdb.iterable.datatype.BatchedKeyStringValueString;
import com.moilioncircle.redis.replicator.rdb.iterable.datatype.BatchedKeyValuePair;

import java.security.Key;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/1/12 10:22
 */
public class HugeKVSocketExample {

    public static void main(String[] args) throws Exception {
        Replicator r = new RedisReplicator("redis://127.0.0.1:6379?authPassword=nkong");
        r.setRdbVisitor(new ValueIterableRdbVisitor(r));
        r.addEventListener(new ValueIterableEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {
                if (event instanceof BatchedKeyValuePair<?, ?>) {
                    // do something
//                    BatchedKeyStringValueString batchedKeyValuePair = (BatchedKeyStringValueString) event;
//                    byte[] value = batchedKeyValuePair.getValue();
//                    System.out.println(new String(value));
                }
                if (event instanceof Command) {
//                    System.out.println(event);
                }
                if (event instanceof KeyStringValueString) {
                    KeyStringValueString keyStringValueString = (KeyStringValueString) event;
                    byte[] value = keyStringValueString.getValue();
                    System.out.println(new String(value));
                }
                if (event instanceof PublishCommand) {
                    PublishCommand publishCommand = (PublishCommand) event;
                    byte[] message = publishCommand.getMessage();
                    System.out.println(new String(message));
                }
            }
        }));
        r.open();
    }

}
