package cn.ann;

import java.util.*;

/**
 * create by 88475 with IntelliJ IDEA on 2019-10-31 14:36
 */
public class CollectionDemo {
    private String[] strings;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;
    private Properties prop;

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public void print() {
        System.out.println(Arrays.toString(strings));
        list.forEach(System.out::println);
        set.forEach(System.out::println);
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        System.out.println(prop);
    }

}
