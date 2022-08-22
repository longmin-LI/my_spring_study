package org.example.thinking.in.spring.ioc.dependency.injection;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于字段的方式进行依赖注入
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired //Autowired他会忽略掉静态字段具体的可以看AutowiredAnnotationBeanPostProcess里面的实现
    private UserHolder userHolder;

    @Resource // 依赖注入可以注入多次
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类) -> Spring Bean
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源，解析并且生成BeanDefinitions
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        //依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyFieldInjectionDemo bean = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);
        //通过字段关联
        System.out.println(bean.userHolder);
        System.out.println(bean.userHolder2);
        //并且这两个对象是相同的
        System.out.println(bean.userHolder == bean.userHolder2);
        applicationContext.close();
    }
    @Bean
    public UserHolder userHolder(User user){
        //setter方法
//        UserHolder holder = new UserHolder();
//        holder.setUser(user);
        //构造器
        return new UserHolder(user);
    }
}
