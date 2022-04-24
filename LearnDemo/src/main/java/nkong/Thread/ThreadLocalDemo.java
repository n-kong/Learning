package nkong.Thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/30 20:39
 *
 * jdbc的连接对象不一定是线程安全的
 * 通过将jdbc对象保存到ThreadLocal对象中，保证每个线程拥有属于自己的连接
 */
public class ThreadLocalDemo {

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal() {
        @Override
        protected Connection initialValue() {
            try {
                return DriverManager.getConnection("URL");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public static Connection getConnection() {
        return connectionThreadLocal.get();
    }


}
