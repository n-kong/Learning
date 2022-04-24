package com.nkong.Singleton;

/**
 * DCL双检查锁实现单例模式
 *
 * @author nkong
 * @version 1.0
 * @date 2021/9/9 16:49
 */
public class Singleton03 {

    private static volatile Singleton03 singleton;

    private Singleton03() {}

    public static Singleton03 getInstance() {
        if (null == singleton) {
            synchronized (Singleton03.class) {
                if (null == singleton) {
                    singleton = new Singleton03();
                }
            }
        }
        return singleton;
    }

}
