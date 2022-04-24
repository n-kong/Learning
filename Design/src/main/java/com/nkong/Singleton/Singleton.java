package com.nkong.Singleton;

/**
 * 单例模式
 *  三大要点：线程安全、延迟加载、序列化与反序列化安全。（双重锁机制实现也可以）
 *  枚举实现的好处：线程安全、单一实例、防止反射攻击
 * @author nkong
 * @version 1.0
 * @date 2021/9/9 15:45
 */
public class Singleton {

    private Singleton() {
    }

    public String getValue() {
        return "hello Singleton";
    }

    public static Singleton getInstance() {
        return SingletonInit.INSTANCE.getSingleton();
    }

    private enum SingletonInit {
        INSTANCE;
        private Singleton singleton;

        SingletonInit() {
            singleton = new Singleton();
        }

        public Singleton getSingleton() {
            return singleton;
        }
    }

}

