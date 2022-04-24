package nkong.gc;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/7/18 13:19
 *
 * 从执行结果可以看出：
 * 第一次发生 GC 时，finalize() 方法的确执行了，并且在被回收之前成功逃脱；
 * 第二次发生 GC 时，由于 finalize() 方法只会被 JVM 调用一次，object 被回收。
 *
 */
public class FinalizerTest {

    public static FinalizerTest object;
    public void isAlive() {
        System.out.println("I'm alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("method finalize is running");
        object = this;
    }

    public static void main(String[] args) throws Exception {
        object = new FinalizerTest();
        // 第一次执行，finalize方法会自救
        object = null;
        System.gc();

        Thread.sleep(500);
        if (object != null) {
            object.isAlive();
        } else {
            System.out.println("I'm dead");
        }

        // 第二次执行，finalize方法已经执行过
        object = null;
        System.gc();

        Thread.sleep(500);
        if (object != null) {
            object.isAlive();
        } else {
            System.out.println("I'm dead");
        }
    }


}
