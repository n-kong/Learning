package nkong.java8.demo;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/9/5 13:11
 */
public class LambdaDemo {

    public static void main(String[] args) {
        getStudent((name, age) -> {
            String s = name + age + "success";
            System.out.println(s);
        });
    }

    public static void getStudent(StudentService studentService) {
        studentService.show("Tome", "23");
    }

    @FunctionalInterface
    interface StudentService {
        void show(String name, String age);
    }

}
