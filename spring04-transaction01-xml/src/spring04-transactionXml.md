## spring 的事务xml配置(事务使用xml配置, 其他的ioc bean使用注解)

---
1. 配置事务管理器
   ```
   <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
       <!-- 引用数据源bean -->
       <property name="dataSource" ref="dataSource"/>
   </bean>
   ```
2. 配置事务的通知
   ```
   <tx:advice id="txAdvice" transaction-manager="transactionManager">
       <tx:attributes>
           <tx:method name="*" propagation="REQUIRED" read-only="false"/>
           <tx:method name="get*" propagation="SUPPORTS" read-only="true"/>
           <tx:method name="find*" propagation="SUPPORTS" read-only="true"/>
       </tx:attributes>
   </tx:advice>
   ```
   * 使用tx:advice标签配置事务通知
     1. 属性：
        * id：事务的唯一标识
        * transaction-manager：给事务通知提供一个事务管理器引用
3. 配置AOP中的通用切入点表达式  
   ```<aop:pointcut id="txPc" expression="execution(* cn.ann.service..*.*(..))"/>```
4. 建立事务通知和切入点表达式的对应关系  
   ```<aop:advisor advice-ref="txAdvice" pointcut-ref="txPc"/>```
5. 配置事务的属性
   * 使用tx:advice标签内部的tx:attributes标签配置事务属性
     ```
     <tx:attributes>
         <tx:method name="*" propagation="REQUIRED" read-only="false"/>
         <tx:method name="get*" propagation="SUPPORTS" read-only="true"/>
         <tx:method name="find*" propagation="SUPPORTS" read-only="true"/>
     </tx:attributes>
     ```
     1. name: 要为什么方法配置事务. * 是通配符
     2. isolation：用于指定事务的隔离级别。默认值是DEFAULT，表示使用数据库的默认隔离级别。
     3. propagation：用于指定事务的传播行为。默认值是REQUIRED，表示一定会有事务，增删改的选择。查询方法可以选择SUPPORTS
     4. read-only：用于指定事务是否只读。只有查询方法才能设置为true。默认值是false，表示读写
     5. timeout：用于指定事务的超时时间，默认值是-1，表示永不超时。如果指定了数值，以秒为单位
     6. rollback-for：用于指定一个异常，当产生该异常时，事务回滚，产生其他异常时，事务不回滚。没有默认值。表示任何异常都回滚
     7. no-rollback-for：用于指定一个异常，当产生该异常时，事务不回滚，产生其他异常时事务回滚。没有默认值。表示任何异常都回滚

## spring 事务的纯注解配置

---
1. 创建需要的bean(数据源, 事务管理器等)
2. 在配置类使用注解开启事务管理: @EnableTransactionManagement
3. 配置通用切入点表达式:
   ```
   @Pointcut("execution(* cn.ann.service..*.*(..))")
   public void txPc(){}
   ```
4. 为每一个需要配置事务的方法配置事务的属性
   ```
   @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
   public void transfer(String source, String target, Double money) {
       ...
   }

   @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
   public Account getAccountByName(String name) throws SQLException {
       ...
   }

   @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
   public Account getAccountById(Integer id) throws SQLException {
       ...
   }
   ```

## spring 的编程式事务控制

---
1. 创建事务管理器bean
   ```
   <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
       <property name="dataSource" ref="dataSource"/>
   </bean>
   ```
2. 创建事务模板bean
   ```
   <bean id="txTemplate" class="org.springframework.transaction.support.TransactionTemplate">
       <property name="transactionManager" ref="transactionManager"/>
   </bean>
   ```
3. 编写业务代码
   ```
   @Service("accountService")
   public class AccountServiceImpl implements AccountService {
       @Resource(name = "txTemplate")
       private TransactionTemplate txTemplate;
       @Resource(name = "accountDao")
       private AccountDao dao;
   
       @Override
       public void transfer(String source, String target, Double money) {
           txTemplate.execute(status -> {
               try {
                   Account sourceAccount = dao.getAccountByName(source);
                   Account targetAccount = dao.getAccountByName(target);
                   sourceAccount.setAccountMoney(sourceAccount.getAccountMoney() - money);
                   dao.updateAccount(sourceAccount);
   //                int i = 1 / 0;
                   targetAccount.setAccountMoney(targetAccount.getAccountMoney() + money);
                   dao.updateAccount(targetAccount);
               } catch (SQLException e) {
                   e.printStackTrace();
               }
               return null;
           });
       }
   
       @Override
       public Account getAccountById(Integer id) throws SQLException {
           return txTemplate.execute(status -> {
               try {
                   return dao.getAccountById(id);
               } catch (SQLException e) {
                   e.printStackTrace();
                   throw new RuntimeException(e);
               }
           });
       }
   
       @Override
       public Account getAccountByName(String name) throws SQLException {
           return txTemplate.execute(status -> {
               try {
                   return dao.getAccountByName(name);
               } catch (SQLException e) {
                   e.printStackTrace();
                   throw new RuntimeException(e);
               }
           });
       }
   }
   ```
* 在代码中可以看到大量重复的部分: txTemplate.execute. 代码用了lambda表达式, 所以感觉并没有那么"震撼", 因为这个原因, 开发中基本不用编程式事务控制

---
在开发中, 关于用注解还是配置文件: 在公司没有硬性要求的情况下, 怎么方便怎么来. 我一般用配置文件配置事务
本片代码: [此处](https://github.com/zhanggaoyu/spring-study) 的所有spring04