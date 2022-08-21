package org.example.thinking.in.spring.ioc.dependency.injection;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于java注解的依赖方法注入示例
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder){
        this.userHolder = userHolder;
    }
    @Resource
    public void initUserHolder2(UserHolder userHolder2){
        this.userHolder2 = userHolder2;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类) -> Spring Bean
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源，解析并且生成BeanDefinitions
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        //依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyMethodInjectionDemo bean = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
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
