package org.example.thinking.in.spring.ioc.dependency.injection;

import org.example.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

public class QualifierAnnotationDependencyInjectionDemo {

    //下面两种都是常规操作
    @Autowired
    private User user; //superUser -> primary

    @Autowired
    @Qualifier("user")//指定Bean的名称
    private User user2;
    //整体应用上下文存在四个User类型的Bean
    //superUser
    //user
    //user1 -> @Qualifier
    //user2 -> @Qualifier
    //下面是特殊的通过分组限定的方法
    @Autowired
    private Collection<User> allUsers;//2个

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;//2个 -> 4个 user1 + user2 + user3 + user4

    @Autowired
    @UserGroup
    private Collection<User> userGroups;//2个 user3 + user4

    @Bean
    @Qualifier// 进行逻辑分组
    public User user1(){
        User user = new User();
        user.setId(7L);
        return user;
    }
    //补充前面的小知识点，就是@Bean方式创建一个Bean的时候，如果没有指定名称默认的就是他的方法名
    @Bean
    @Qualifier// 进行逻辑分组
    public User user2(){
        User user = new User();
        user.setId(8L);
        return user;
    }
    @Bean
    @UserGroup// 进行逻辑分组
    public User user3(){
        User user = new User();
        user.setId(9L);
        return user;
    }
    @Bean
    @UserGroup// 进行逻辑分组
    public User user4(){
        User user = new User();
        user.setId(10L);
        return user;
    }
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）-> Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源，解析并且生成BeanDefinitions
        reader.loadBeanDefinitions(location);
        //启动应用上下文
        applicationContext.refresh();
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        //期待输出superUser
        System.out.println(demo.user);
        //期待输出user
        System.out.println(demo.user2);
        //期待输出superUser user
        System.out.println("allUsers: " + demo.allUsers);
        //期待输出user1 user2
        System.out.println(demo.qualifierUsers);
        //期待输出user3 user4
        System.out.println(demo.userGroups);
        applicationContext.close();
    }
}
