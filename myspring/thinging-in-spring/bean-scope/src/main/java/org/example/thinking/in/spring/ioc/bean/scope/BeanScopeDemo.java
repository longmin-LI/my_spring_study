package org.example.thinking.in.spring.ioc.bean.scope;

import com.sun.javafx.scene.EnteredExitedHandler;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

public class BeanScopeDemo implements DisposableBean {
    //以@Bean的方法的形式来定义Bean方法名称就是Bean的名称
    @Bean
    @Qualifier("singletonUser")
    public static User singletonUser(){
        return getUser();
    }

    @Bean
    @Qualifier("prototypeUser")
    @Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return getUser();
    }
    public static User getUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    //key就是Bean的名称
    //结论二：如果依赖注入的是集合对象，Singleton和prototype都只有一个，而且集合里面的prototype的user对象区别于其他属性的值，即无论怎么样都会生成新的对象
    private Map<String,User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;//Resolvable dependency
    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称:%s 在初始化后回调...%n",bean.getClass().getName(),beanName);
                    return bean;
                }
            });
        });
        //启动
        applicationContext.refresh();

        scopeBeanByLookup(applicationContext);
        //结论一：singleton无论依赖查找还是依赖注入都是同一个对象
        //对于prototype都是新对象
        scopeBeanByInjection(applicationContext);
        //关闭
        applicationContext.close();
    }

    public static void scopeBeanByLookup(AnnotationConfigApplicationContext applicationContext){

        for(int i = 0;i < 3;i++){
            //singleton共享Bean对象
            User singletonUser = applicationContext.getBean("singletonUser",User.class);
            System.out.println(singletonUser);
            //prototype是不同的
            User prototypeUser = applicationContext.getBean("prototypeUser",User.class);
            System.out.println(prototypeUser);
        }

    }

    public static void scopeBeanByInjection(AnnotationConfigApplicationContext applicationContext){

        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);

        System.out.println(beanScopeDemo.singletonUser);
        System.out.println(beanScopeDemo.prototypeUser);
        System.out.println(beanScopeDemo.prototypeUser1);
        System.out.println(beanScopeDemo.prototypeUser2);
        System.out.println(beanScopeDemo.users);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("当前BeanScopeDemo bean正在销毁中");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        //获取BeanDefinition
        for(Map.Entry<String,User> entry : this.users.entrySet()){
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if(beanDefinition.isPrototype()){
                User user = entry.getValue();
                user.destroy();
            }
        }
        System.out.println("当前BeanScopeDemo Bean销毁完毕");
    }
}
