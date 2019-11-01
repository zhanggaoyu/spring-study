package cn.ann.beans;

import java.io.Serializable;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-1 16:03
 */
public class User implements Serializable {
    private static final long serialVersionUID = -8891741332118707583L;

    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
