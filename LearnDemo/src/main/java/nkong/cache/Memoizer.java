package nkong.cache;

import java.util.concurrent.*;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/7/10 21:33
 *
 * 构建高效且可伸缩的结果缓存
 */
public class Memoizer<A, V> implements Computable<A, V> {

    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A args) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(args);
            if (null == f) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(args);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(args, ft);
                if (null == f) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(args);
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
        }
    }
}
