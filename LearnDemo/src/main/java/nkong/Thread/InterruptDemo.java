package nkong.Thread;

import static java.lang.Thread.interrupted;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/7/17 12:40
 */
public class InterruptDemo {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Worker());
        t.start();
        Thread.sleep(200);
        t.interrupt();
        System.out.println("Main thread stopped.");
    }
    public static class Worker implements Runnable {
        public void run() {
            System.out.println("Worker started.");
            while (!Thread.currentThread().isInterrupted()){
                try {
                    System.out.println("thread state -- " + interrupted());
                    Thread.sleep(53);
                } catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println("Worker stopped.");
        }
    }


}
