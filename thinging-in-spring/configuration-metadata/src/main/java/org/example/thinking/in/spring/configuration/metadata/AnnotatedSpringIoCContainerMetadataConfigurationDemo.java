package org.example.thinking.in.spring.configuration.metadata;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;
//这个注解可是不加
@Configuration
@ImportResource(value = "META-INF/dependency-lookup-context.xml")
@Import(User.class)
//这个注解可以重复注解
@PropertySource(value = "META-INF/users-config.properties")
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {
    @Bean
    public User configuredUser(@Value("${user.id}") Long id,@Value("${user.name}") String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }


    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前类，作为Configuration
        applicationContext.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);

        applicationContext.refresh();

        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);

        for(Map.Entry<String,User> entry : beansOfType.entrySet()){
            System.out.printf("User bean name %s, content %s \n", entry.getKey(), entry.getValue());
        }

        applicationContext.close();
    }
}
