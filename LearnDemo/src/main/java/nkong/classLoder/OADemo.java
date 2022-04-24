package nkong.classLoder;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/7/22 21:55
 */
public class OADemo {

    public static void main(String[] args) throws Exception {
        Double salary = 2000.00;
        Double money = null;

        // 1 urlClassLoader方式加载本地jar
//        String url = "file:D:\\workspace\\SalaryCaler\\target\\SalaryCaler-1.0-SNAPSHOT.jar";
//        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL(url)});
//        while (true) {
//            money = calSalary(salary, urlClassLoader);
//            System.out.println("实际到手工资：" + money);
//            Thread.sleep(1000);
//        }

        // 2 自定义类加载器实现加载本地class文件
//        SalaryClassLoader salaryClassLoader = new SalaryClassLoader("D:\\workspace\\SalaryCaler\\target\\classes\\");
//        while (true) {
//            money = calSalary(salary, salaryClassLoader);
//            System.out.println("实际到手工资：" + money);
//            Thread.sleep(1000);
//        }

        // 3 自定义类加载器实现加载本地jar包中的class文件
        while (true) {
            SalaryJarLoader salaryJarLoader = new SalaryJarLoader("D:\\workspace\\SalaryCaler\\target\\SalaryCaler-1.0-SNAPSHOT.jar");
            money = calSalary(salary, salaryJarLoader);
            System.out.println("实际到手工资：" + money);
            Thread.sleep(1000);
        }
    }

    private static Double calSalary(Double salary, ClassLoader classLoader) throws Exception {

        Class<?> loadClass = classLoader.loadClass("com.nkong.SalaryCaler");
        Object obj = loadClass.newInstance();
        return (Double) loadClass.getMethod("cal", Double.class).invoke(obj, salary);

//        SalaryCaler salaryCaler = new SalaryCaler();
//        return salaryCaler.cal(salary);

    }

}
