package nkong.redis;

import com.moilioncircle.redis.replicator.Configuration;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.event.Event;
import com.moilioncircle.redis.replicator.event.EventListener;
import redis.clients.jedis.HostAndPort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/1/12 10:06
 */
public class SentinelExample {

    public static void main(String[] args) throws Exception {
        List<HostAndPort> hosts = new ArrayList<>();
        // sentinel hosts
        hosts.add(new HostAndPort("127.0.0.1", 6379));
        Replicator replicator = new RedisSentinelReplicator(hosts, "mymaster", Configuration.defaultSetting());
        replicator.addEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {
                System.out.println(event);
            }
        });
        replicator.open();
    }
}
