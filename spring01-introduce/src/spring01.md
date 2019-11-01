## 耦合

---
1. 什么是耦合?
   * 耦合表示类与类之间的联系, 类与类之间的联系越强, 耦合度越高
2. 通过例子来说明
   * 我们写了两个类, 其中beanService中需要用到beanDao来实现功能
     * beanDao接口和实现类
       ```
       public interface BeanDao {
           // 模拟add方法
           void add();
       }
       
       public class BeanDaoImpl implements BeanDao {
           @Override
           public void add() {
               System.out.println("添加bean信息");
           }
       }
       ```
     * beanService接口和实现类
       ```
       public interface BeanService {
           void add();
       }
       
       public class BeanServiceImpl implements BeanService {
           BeanDao dao = new BeanDaoImpl();
           @Override
           public void add() {
               dao.add();
           }
       }
       ```
     * 测试类及运行结果
       ```
       public class Demo {
           @Test
           public void demo01() {
               BeanService service = new BeanServiceImpl();
               service.add();
           }
       }
       ```
       ![运行结果](https://images.cnblogs.com/cnblogs_com/ann-zhgy/1558457/o_y.png)
     ---
     * 从代码结构中可以看出: beanService中没有beanDao的耦合度很高, 如果没有BeanDao的实现类, 编译时就会报错

<br>

   * 改造一下, 我们可以用工厂模式来解耦
     * 工厂类代码:
       ```
       public class BeanFactory {
           public static Object getInstance(String className) {
               try {
                   Class<?> clazz = Class.forName(className);
                   Object obj = clazz.newInstance();
                   return obj;
               } catch (Exception e) {
                   e.printStackTrace();
                   return null;
               }
           }
       }
       ```
     * 有了工厂类代码以后我们就可以改造一下beanServiceImpl
       ```
       public class BeanServiceImpl implements BeanService {
           BeanDao dao = (BeanDao) BeanFactory.getInstance("cn.ann.dao.impl.BeanDaoImpl");
           
           @Override
           public void add() { dao.add(); }
       }
       ```
     * 运行结果还是一样的

     ---
     * 通过改造, BeanServiceImpl和BeanDaoImpl的耦合已经降低, 最直观的效果就是如果没有BeanDaoImpl这个类的话, 程序在编译期间不会报错
     * 但是, 还有一些问题: className是写死的, 如果我们的实现类不在这个路径, 或者是不叫这个名字, 就没办法通过工厂获取这个类的对象了

<br>

   * 继续改造, 加配置文件是不错的选择
     * 配置文件bean.properties
       ```
       beanDao=cn.ann.dao.impl.BeanDaoImpl
       ```
     * beanFactory
       ```
       public class BeanFactory {
           private static Map<String, String> props = new HashMap<>();
              
           static {
               try (InputStream is = BeanFactory.class.getClassLoader().
                       getResourceAsStream("bean.properties")) {
                   Properties prop = new Properties();
                   prop.load(is);
                   Set<String> keys = prop.stringPropertyNames();
                   keys.forEach((key) -> props.put(key, prop.getProperty(key)));
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        
           public static Object getInstance(String key) {
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
           }
       }
       ```
       * 通过改造, 我们可以将BeanServiceImpl中成员变量的初始化改成```BeanDao dao = (BeanDao) BeanFactory.getInstance("beanDao");```

     ---
     * 通过加配置文件, 我们可以通过修改配置文件的方式来修改BeanDao的实现类. 那么还有什么问题呢?
       * 如果出现了频繁使用beanServiceImpl的场景的话, 我们是不是要创建很多类呢? 或许我们可以让创建出来的对象成为单例的

<br>

   * 再次改造
     * BeanFactory
       ```
       public class BeanFactory {
           private static Map<String, Object> instances = new HashMap<>();
        
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
        
           public static Object getInstance(String key) {
               if (instances.containsKey(key)) {
                  return instances.get(key);
               } else {
                   throw new RuntimeException("配置文件中不存在该key");
               }
           }
       }
       ```
     * 运行结果:  
       ![运行结果](https://images.cnblogs.com/cnblogs_com/ann-zhgy/1558457/o_singleton-res.png)
       * 通过运行结果可以看出, BeanDaoImpl是同一个对象

---
本篇到此结束, 这篇文章的重点是关于耦合这方面的, 所以并没有考虑关于线程的问题  
本篇代码下载链接: [此处](https://github.com/zhanggaoyu/spring-study) 的 spring01-introduce
