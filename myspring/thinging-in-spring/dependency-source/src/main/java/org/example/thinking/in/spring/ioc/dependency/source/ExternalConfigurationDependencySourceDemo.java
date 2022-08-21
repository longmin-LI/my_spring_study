package org.example.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
@PropertySource(value = "MATE-INF/default.properties",encoding = "UTF-8")
@Configuration
public class ExternalConfigurationDependencySourceDemo {
    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;
    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;
    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        //启动
        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo bean = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println(bean.id);
        System.out.println(bean.name);
        System.out.println(bean.resource);
        //关闭
        applicationContext.close();
    }
}
