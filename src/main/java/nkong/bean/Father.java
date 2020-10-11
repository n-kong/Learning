package nkong.bean;

/**
 * @Author nkong
 * @Date 2020/7/3 22:21
 * @Version 1.0
 **/
public abstract class Father {


    public void play() {}

    public void sleep() {};

}

class Son extends Father {

    @Override
    public void play() {
        System.out.println("My is son");
    }

    public static void main(String[] args) {
        Father father = new Son();
        father.play();
    }

}
