## spring03-AOP

---
1. 术语:
   1. joinPoint(连接点):  
      连接点指的是被拦截的点, 在spring中指的就是配置了动态代理的类中的所有方法
   2. pointCut(切入点):  
      切入点就是被增强的方法 
   3. advice(通知):  
      通知指的就是增强方法具体做了什么, 实际上就是指提供了代理方法的类, 分为前置通知, 后置通知, 异常通知, 最终通知, 环绕通知
   4. introduction(引介):  
      引介是一种特殊的通知. 在不修改类代码的前提下, introduction可以在运行期间为类动态的添加一些方法或field
   5. target(目标对象):  
      被代理对象
   6. weaving(织入):  
      指把增强应用到目标对象产生代理对象的过程.  
      spring采用动态代理植入, aspectJ使用编译期织入和类装载期织入
   7. proxy(代理):  
      一个类被aop织入后产生的代理对象
2. aop的xml配置步骤
   1. 将通知类添加到spring容器
   2. 使用 aop:config 标签开始配置aop
   3. 使用 aop:aspect 标签配置切面
      1. id: 唯一标识
      2. ref: 指定通知类的bean的id
   4. 在 aop:aspect 内部使用相应标签配置通知类型
      1. 通知标签: 
         * method: 指定通知方法
         * pointcut: 指定切入点表达式, 用于指定对哪些方法进行加强
           切入点表达式写法: execution(访问修饰符 返回值 全限定方法名(参数表))
           * public void cn.ann.service.AccountService.saveAccount()
           * 其中: 
             1. 访问修饰符可以省略
             2. 返回值可以使用通配符, 表示任意返回值
             3. 包名可以只用通配符, 表示任意包
             4. 包名可以使用 .. 表示当前包及其子包
             5. 类名和方法名也可以使用通配符
             6. 参数表: 
                * 可以直接写数据类型: 基本类型直接写名称: int; 引用类型写全限定类名
                * 可以使用通配符代表所有参数, 但是方法必须有参数才可以
                * 可以使用 .. 表示有无参数均可, 有参数可以是任意类型
           * 全通配写法: \* \*..\*.\*(..)
           * 开发中的常用写法:   
             \* 业务层包名..\*.\*(..)  
             \* cn.ann.service..\*.\*(..)
   5. 通知标签
      1. aop:before: 前置通知. 切入点执行前执行
      2. aop:after-returning: 后置通知: 切入点正常执行后执行
      3. aop:after-throwing: 异常通知: 切入点产生异常后执行
      4. aop:after: 最终通知: 切入点执行后执行(不论是否产生异常)
      5. aop:around: 环绕通知: spring为我们提供的可以 **让我们手动设置通知什么时候执行** 的方式
         * 方法设计: 
           ```
           public Object aroundLogging(ProceedingJoinPoint pjp) {
               try {
                   System.out.println("前置 ...");
                   Object ret = pjp.proceed();
                   System.out.println("后置 ...");
                   return ret;
               } catch (Throwable throwable) {
                   System.out.println("异常 ...");
                   throw new RuntimeException(throwable);
               } finally {
                   System.out.println("最终 ...");
               }
           }
           ```
      * 后置通知和异常通知只能执行一个
   6. 配置切入点 
      ```
      <aop:config>
          <!-- 配置切入点 -->
          <aop:pointcut id="logPointcut" expression="execution(* cn.ann.service..*.*(..))"/>
      </aop:config>
      ```
   
   * aop配置代码
     ```
     <!-- 配置AOP -->
     <aop:config>
         <!-- 配置切入点 -->
         <aop:pointcut id="logPointcut" expression="execution(* cn.ann.service..*.*(..))"/>
         <!-- 配置切面 -->
         <aop:aspect id="logAdvice" ref="logger">
             <!-- 前置通知
             <aop:before method="beforeLogging" pointcut-ref="logPointcut"/> -->
             <!-- 后置通知
             <aop:after-returning method="afterReturningLogging" pointcut-ref="logPointcut"/> -->
             <!-- 异常通知
             <aop:after-throwing method="afterThrowingLogging" pointcut-ref="logPointcut"/> -->
             <!-- 最终通知
             <aop:after method="afterLogging" pointcut-ref="logPointcut"/> -->
             <!-- 环绕通知 -->
             <aop:around method="aroundLogging" pointcut-ref="logPointcut"/>
         </aop:aspect>
     </aop:config>
     ```
3. aop注解配置
   1. 开启注解扫描
      * ```<context:component-scan base-package="cn.ann"/>```
   2. 开启aop注解
      * ```<aop:aspectj-autoproxy/>```
   3. 配置通知
      1. 将通知类添加到spring容器中: @Component("logger")
      2. 声明通知类: @Aspect
      3. 配置切入点: 
         ```
         @Pointcut("execution(* cn.ann.service..*.*(..))")
         private void logPointcut(){}
         ```
      4. 配置通知
         ```
         @Before("logPointcut()")
         public void beforeLogging(){
             System.out.println("before logging run...");
         }
     
         @AfterReturning("logPointcut()")
         public void afterReturningLogging(){
             System.out.println("afterReturning logging run...");
         }
     
         @AfterThrowing("logPointcut()")
         public void afterThrowingLogging(){
             System.out.println("afterThrowing logging run...");
         }
     
         @After("logPointcut()")
         public void afterLogging(){
             System.out.println("after logging run...");
         }
     
         @Around("logPointcut()")
         public Object aroundLogging(ProceedingJoinPoint pjp) {
             try {
                 System.out.println("前置 ...");
                 Object ret = pjp.proceed();
                 System.out.println("后置 ...");
                 return ret;
             } catch (Throwable throwable) {
                 System.out.println("异常 ...");
                 throw new RuntimeException(throwable);
             } finally {
                 System.out.println("最终 ...");
             }
         }
         ```
      * 注意: 
        1. 分开配置 前置通知, 后置通知, 异常通知, 最终通知 运行会有点问题, 方法的调用顺序回头有点问题  
           ![运行结果](https://images.cnblogs.com/cnblogs_com/ann-zhgy/1558457/o_aop-annotation.png)
        2. 开发中一般使用环绕通知  
           ![运行结果](https://images.cnblogs.com/cnblogs_com/ann-zhgy/1558457/o_annotation-around.png)

---
本文代码: [此处](https://github.com/zhanggaoyu/spring-study) 的 spring03-aop