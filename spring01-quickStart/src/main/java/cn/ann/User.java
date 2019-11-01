package cn.ann;

import java.util.Date;

/**
 * create by 88475 with IntelliJ IDEA on 2019-10-30 21:48
 */
public class User {
    private String name;
    private Integer age;
    private Date birthday;

    public User() {
    }

    public User(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void init() {
        System.out.println("init run...");
    }

    public void destroy() {
        System.out.println("destroy run...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
