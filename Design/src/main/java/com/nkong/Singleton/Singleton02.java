package com.nkong.Singleton;

/**
 * 静态内部类实现单例模式
 *
 * @author nkong
 * @version 1.0
 * @date 2021/9/9 16:36
 */
public class Singleton02 {

    private Singleton02() {
    }

    private static class SingletonHolder {
        private static final Singleton02 INSTANCE = new Singleton02();
    }

    public static Singleton02 getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }

}
