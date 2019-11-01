package cn.ann;

/**
 * create by 88475 with IntelliJ IDEA on 2019-10-30 14:37
 */
public class Hello {
    private String name;

    public Hello() {
    }

    public Hello(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("hello spring" + "name 为 " + name);
    }

}
