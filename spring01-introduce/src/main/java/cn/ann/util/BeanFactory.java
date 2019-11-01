package cn.ann.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * create by 88475 with IntelliJ IDEA on 2019-10-30 20:54
 */
public class BeanFactory {
//    private static Map<String, String> props = new HashMap<>();
    private static Map<String, Object> instances = new HashMap<>();

    /*public static Object getInstance(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

    /*static {
        try (InputStream is = BeanFactory.class.getClassLoader().
                getResourceAsStream("bean.properties")) {
            Properties prop = new Properties();
            prop.load(is);
            Set<String> keys = prop.stringPropertyNames();
            keys.forEach((key) -> props.put(key, prop.getProperty(key)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    static {
        try (InputStream is = BeanFactory.class.getClassLoader().
                getResourceAsStream("bean.properties")) {
            Properties prop = new Properties();
            prop.load(is);
            Set<String> keys = prop.stringPropertyNames();
            keys.forEach((key) -> {
                try {
                    instances.put(key, Class.forName(prop.getProperty(key)).newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static Object getInstance(String key) {
        if (props.containsKey(key)) {
            try {
                Class<?> clazz = Class.forName(props.get(key));
                Object obj = clazz.newInstance();
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new RuntimeException("配置文件中不存在该key");
        }
    }*/

    public static Object getInstance(String key) {
        if (instances.containsKey(key)) {
           return instances.get(key);
        } else {
            throw new RuntimeException("配置文件中不存在该key");
        }
    }

}
