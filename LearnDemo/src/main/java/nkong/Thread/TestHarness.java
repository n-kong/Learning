package nkong.Thread;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/7/7 22:24
 *
 * 闭锁 一种同步工具类
 * 允许一个或多个线程等待直到在其他线程中一组操作执行完成
 */
public class TestHarness {


    public static void main(String[] args) throws InterruptedException {
        long l = timeTask(5, new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("开始执行任务");
                Thread.sleep(2000L);
                System.out.println("任务执行结束");
            }
        });
        System.out.println("耗时：" + l);
    }


    public static long timeTask(int threadNum, final Runnable task) throws InterruptedException {
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        start.await();
                        try {
                            task.run();
                        } finally {
                            end.countDown();
                        }
                    } catch (InterruptedException e) {
                    }
                }
            };
            t.start();
        }

        long startTime = System.nanoTime();
        start.countDown();
        end.await();
        long endTime = System.nanoTime();
        return endTime - startTime;

    }

}
