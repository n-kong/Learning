package nkong.redis;

import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.event.Event;
import com.moilioncircle.redis.replicator.event.EventListener;
import com.moilioncircle.redis.replicator.rdb.dump.DumpRdbVisitor;
import com.moilioncircle.redis.replicator.rdb.dump.datatype.DumpKeyValuePair;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/1/9 17:06
 */
public class RdbToDump {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Replicator r = new RedisReplicator("redis:///D:/soft/Redis/dump.rdb");
        r.setRdbVisitor(new DumpRdbVisitor(r));
        r.addEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {
                if (!(event instanceof DumpKeyValuePair)) return;
                DumpKeyValuePair dkv = (DumpKeyValuePair) event;
                byte[] serialized = dkv.getValue();
                System.out.println(new String(serialized));
                // we can use redis RESTORE command to migrate this serialized value to another redis.
            }
        });
        r.open();
    }

}
