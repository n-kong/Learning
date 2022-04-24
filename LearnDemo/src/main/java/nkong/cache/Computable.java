package nkong.cache;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/7/10 21:32
 */
public interface Computable<A, V> {

    V compute(A args) throws InterruptedException;

}
