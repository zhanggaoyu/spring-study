## spring

---
1. spring是什么
   * Spring是一个开源框架
   * spring可以简化企业级应用开发
   * spring是一个以IoC（Inverse Of Control：反转控制）和AOP（Aspect Oriented Programming：面向切面编程）为核心的框架
2. 特点
   * 轻量级: spring是非侵入性的. 基于spring开发的应用中的对象可以不依赖于spring的API
   * 依赖注入: (DI ---> dependency injection, IOC)
   * 面向切面编程(AOP)
   * 容器: spring是一个容器, 因为它包含并管理应用对象的生命周期
     ...
3. spring模块  
   ![spring模块](https://images.cnblogs.com/cnblogs_com/ann-zhgy/1558457/o_spring-model.jpg)

## hello

---
1. 准备
   * 引入依赖
     ```
     <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-context</artifactId>
         <version>5.1.8.RELEASE</version>
     </dependency>
     ```
   * 创建Hello类
     ```
     public class Hello {
         public void hello() {
             System.out.println("hello spring");
         }
     }
     ```
   * 编写配置文件
     ```
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
         <bean id="hello" class="cn.ann.Hello"/>
     </beans>
     ```
     * beans 标签后面的一大坨是固定写法, 无脑复制粘贴就好
     * bean 标签中, id 是唯一标识, 一般将类名首字母小写, class 是类的全类名
2. 编写测试类测试
   ```
   @Test
   public void demo01() {
       ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
       Hello hello = (Hello) ac.getBean("hello");
       hello.hello();
   }
   ```
   运行结果:  
   ![运行结果](https://images.cnblogs.com/cnblogs_com/ann-zhgy/1558457/o_20191030163306.png)

## IOC & DI

---
1. 是什么?
   * IOC: Ioc—Inversion of Control，即“控制反转”，不是什么技术，而是一种设计思想。在Java开发中，Ioc意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。 这种设计思想是为了降低程序的耦合
   * DI: Dependency Injection，即“依赖注入”：是组件之间依赖关系由容器在运行期决定，形象的说，即由容器动态的将某个依赖关系注入到组件之中。
   * 想了解更多可以看Iteye的开涛这位技术牛人对Spring框架的IOC的理解, 附上链接: [http://jinnianshilongnian.iteye.com/blog/1413846](http://jinnianshilongnian.iteye.com/blog/1413846)

## 配置文件和测试类分析

---
1. 测试类代码分析
   * 测试类中第一行代码 ```new ClassPathXmlApplicationContext(配置文件路径)``` 是对IOC容器初始化, 其返回值可以用ApplicationContext或BeanFactory接收
     1. BeanFactory: 创建对象时采用 **延迟加载** 的策略, 即: 第一次获取对象时才创建. 适用于单例模式
     2. ApplicationContext: 创建对象时采用 **立即加载** 的策略, 即: 读取完配置文件后就加载对象. 适用于多例模式
   * 测试类中第二行代码 ```ac.getBean("hello")``` 可以获取IOC容器中的指定对象, 参数可以是id值, 也可以是Class对象
     1. 参数是 id: 获取指定id值映射的bean
     2. 参数是 Class 对象: 获取相应的bean, 但是容器中只能有一个该类的 bean, 即: 容器中只能有一个class属性为该类型的bean标签
2. spring对bean的管理细节
   1. 创建bean的三种方式
      1. 使用默认构造函数创建
         ```<bean id="user" class="cn.ann.User"/>```
         * 在spring的配置文件中使用bean标签, 配以id和class以后, 且没有其他标签时, 采用的就是默认构造函数创建bean对象, 如果类中没有默认的无参构造函数, 对象就无法创建
      2. 使用普通工厂的方法创建
         ```
         <bean id="userFactory" class="cn.ann.UserFactory"/>
         <bean id="user" factory-bean="userFactory" factory-method="getUser"/>
         ```
      3. 使用静态工厂方法创建  
         ```<bean id="user" class="cn.ann.StaticFactory" factory-method="getUser"/>```
   2. bean对象的作用范围
      * bean标签的scope属性
        1. singleton: 单例(默认)
        2. prototype: 原型, 就是多例
        3. request: 作用于web应用的请求范围
        4. session: 作用于web应用的会话范围
        5. global-session: 作用于集群环境的会话范围(全局会话范围), 当不是集群时, 就是session
   3. bean对象的生命周期
      1. singleton:
         * 创建: 随容器的创建而创建
         * 销毁: 随容器的销毁而销毁
         * 总结: 与容器的生命周期相同
      2. prototype:
         * 创建: 第一次使用对象时创建
         * 销毁: 通过Java的垃圾回收器回收
3. spring的依赖注入
   1. 是什么?
      * 当前类需要用到的其它类的对象, 有spring为我们提供, 我们只需要在配置文件中维护依赖关系即可
   2. 能注入的数据: 有三种
      1. 基本数据类型和String
      2. 其他bean类型
      3. 复杂类型, 集合类型
   3. 注入方式: 有三种(注意, 经常变化的数据不适合注入)
      1. 使用构造函数
      2. 使用set方法
      3. 使用注解

   * 使用构造函数注入
     ```
     <bean id="user" class="cn.ann.User">
         <constructor-arg name="" value="" type="" index=""></constructor-arg>
     </bean>
     ```
     * 需使用 constructor-arg 标签, 属性:
       * type: 为要插入的数据指定类型, 该参数也是构造函数中某个或某些参数的类型
       * index: 为要插入的数据指定索引位置, 0开始
       * name: 为构造方法中指定名称的参数赋值
       * **以上三个用于指定给构造参数哪个参数赋值, 但是最常用的时name**
       * value: 用于提供基本类型和其他数据类型
       * ref: 用于引用其他的bean类型, 必须是IOC容器中有的bean对象
       ---
       * 优势: 若对象没有提供无参构造器, 那么在获取bean的时候诸如数据是必须的操作, 否则对象无法创建成功
       * 弊端: 如果数据不是必需的, 就不能创建对象
   * 使用set注入(更常用)
     ```
     <bean id="user" class="cn.ann.User">
         <property name="name" value="zs"/>
         <property name="age" value="23"/>
         <property name="birthday" ref="date"/>
     </bean>
     ```
     * 需使用property标签, 属性:
       * name: 用于指定注入的属性名称
       * value: 用于指定注入的属性的值
       * ref: 用于引用其他的bean类型, 必须是IOC容器中有的bean对象
       ---
       * 优势: 创建对象时直接调用默认构造函数就好, 没有明确的限制
       * 弊端: 获取对象时set方法可能没有执行
   * 复杂类型注入
     * bean属性(省略了getter,setter和toString):
       ```
       public class CollectionDemo {
           private String[] strings;
           private List<String> list;
           private Set<String> set;
           private Map<String, String> map;
           private Properties prop;
       }      
       ```
     1. array类型(String[])
        ```
        <property name="strings">
            <array>
                <value>aaa</value>
                <value>bbb</value>
                <value>ccc</value>
            </array>
        </property>
        ```
     2. List类型
        ```
        <property name="list">
            <list>
                <value>AAA</value>
                <value>BBB</value>
                <value>CCC</value>
            </list>
        </property>
        ```
     3. Set类型
        ```
        <property name="set">
            <set>
                <value>111</value>
                <value>222</value>
                <value>333</value>
            </set>
        </property>
        ```
     4. Map类型
        ```
        <property name="map">
            <map>
                <entry key="key01" value="val01"/>
                <entry key="key02" value="val02"/>
                <entry key="key03" value="val03"/>
            </map>
        </property>
        ```
     5. Properties类型
        ```
        <property name="prop">
            <props>
                <prop key="prop01">val01</prop>
                <prop key="prop02">val02</prop>
                <prop key="prop03">val03</prop>
            </props>
        </property>
        ```
     ---
   * 注意: array, list和set都可以对list结构进行注入; entry和props都可以对Map结构进行注入

---
代码链接: [此处](https://github.com/zhanggaoyu/spring-study) 的 spring01-quickStart