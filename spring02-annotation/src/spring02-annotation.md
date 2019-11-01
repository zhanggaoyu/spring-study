## spring的IOC注解

---
曾经的xml中的bean标签配置
```
<bean id="" class="" init-method="" destroy-method="" scope="">
   <property name="" value="" | ref=""/>
</bean>
```
依据标签可以对注解进行分类: 

1. 用于创建对象的
   * 其作用和xml中的bean标签一样
   1. @Component: 
      * 位置: 类上
      * 作用: 将当前对象类存入spring容器中
      * value属性: 相当于设置bean标签的id属性, 默认值为类名首字母小写
   2. @Controller, @Service, @Repository作用和@Component一样, 分别用在控制层, 服务层和持久层(其实底层就是继承机制)
2. 用于注入数据的
   * 其作用和xml中bean的子标签property一样
   * 1~3只能注入bean类型的数据, 集合类型只能通过xml注入
   1. @AutoWired
      * 位置: 变量或方法上
      * 作用: 自动按照类型注入. 只要容器中有唯一的bean对象的类型与要注入的变量类型匹配, 就可以注入成功
      * 注意: 
        1. 如果IOC容器中没有任何bean的类型与要注入的变量类型相匹配, 就会报错
        2. 如果IOC容器中有多个bean类型与要注入的变量类型相匹配时, 首先会按照匹配的类型找bean对象, 然后会按照id找与变量名相同的bean对象, 否则会报错
   2. @Qualifier
      * 位置: 变量|方法|方法参数上, 需要与@Autowired一起使用
      * 作用: 在按照类型注入的基础上再按照变量名注入. 给类成员注入时不能单独使用, 但是给方法参数注入时可以
      * 属性: 用于指定注入的bean的id
   3. @Resource(name = "beanDao")
      * 位置: 变量或方法上, 需要与@Autowired一起使用
      * 作用: 直接按照bean的id注入. 可以独立使用
      * 属性:
        1. name: 用于指定bean的id
   4. @Value
      * 作用: 用于注入基本数据类型和String类型的数据
      * 属性: 用于指定数据的值. 可以使用spEl(EL表达式, 出现在什么地方就是什么的表达式)
3. 用于改变作用范围的
   * 其作用和xml中bean标签的scope属性一样
   1. @Scope
      * 属性: 指定范围的取值. 常用的有singleton和prototype
4. 和生命周期相关的(了解)
   * 其作用和xml中bean标签的init-method和destroy-method属性一样
   1. @PreDestroy: 指定销毁方法
   2. @PreConstruct: 指定初始化方法