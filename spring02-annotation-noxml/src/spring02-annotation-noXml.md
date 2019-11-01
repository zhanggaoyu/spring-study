## spring纯注解开发

---
* 说明: 本文是基于一个spring+DBUtils写的数据库CURD操作的小demo, 地址: [此处](https://github.com/zhanggaoyu/spring-study) 的 spring02-annotation-demo
1. @Configuration: 指明当前类时一个配置类
   * 细节: 当前类是AnnotationConfigApplicationContext的参数时, 该注解可以不写
2. 测试代码:
   ```
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration(classes = SpringConfiguration.class)
   public class Demo {
       @Resource(name = "userService")
       private UserService service;
   
       @Test
       public void demo01() throws SQLException {
           List<User> users = service.getUsers();
           users.forEach(System.out::println);
       }
   }
   ```
   * @RunWith: 指定运行器
   * @ContextConfiguration: locations指定配置文件路径, classes指定配置类
3. @ComponentScan: 指明spring创建容器时要扫描的包
   * 属性: value|basePackages两个属性作用一样, 用于指定包
   * 使用此注解就等同于在xml中配置了```<context:component-scan base-package="cn.ann"/>``
4. @Bean: 用于把当前方法的返回值作为bean对象存入spring容器中
   * 属性: name ---> 用于指定当前bean的id. 默认是方法名
   * 细节: 当我们使用注解配置方法时, 如果方法有参数, spring框架会去容器中查找有没有可用的bean对象. 查找方式与@Autowired注解相同
5. @Import: 用于导入其他的配置类
   * 属性: 要导入的配置类的字节码对象
   * 有该注解的类是主配置|父配置类
6. @PropertySource: 用指定外部的properties文件
   * 属性: properties文件地址. 用classpath来指定是类路径
   
---
对于注解开发和配置文件开发, 在开发中, 哪种方式方便, 我们就选那种, 两种方式结合会比较多
本文代码地址: [此处](https://github.com/zhanggaoyu/spring-study) 的 spring02-annotation-noxml
