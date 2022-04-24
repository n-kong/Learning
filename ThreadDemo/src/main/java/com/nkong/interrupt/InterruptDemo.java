package com.nkong.interrupt;

import lombok.SneakyThrows;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/8/8 17:30
 */
public class InterruptDemo implements Runnable{

    private volatile static boolean on = false;
    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptDemo(),"InterruptDemo");
        testThread.start();
        Thread.sleep(1000);
        InterruptDemo.on = true;
        testThread.interrupt();
        System.out.println("main end");

    }

    @Override
    public void run() {
        while(!on){
            try {
                Thread.sleep(10000000L);
            } catch (InterruptedException e) {
                System.out.println("caught exception: "+e);
            }
        }
    }

}
