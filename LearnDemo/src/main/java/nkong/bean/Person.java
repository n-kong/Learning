package nkong.bean;

import com.alibaba.fastjson.JSON;

/**
 * @Author nkong
 * @Date 2020/7/3 22:12
 * @Version 1.0
 **/
public class Person {

    private String name;
    private String age;
    private String play;

    public Person(String name, String age, String play) {
        this.name = name;
        this.age = age;
        this.play = play;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
