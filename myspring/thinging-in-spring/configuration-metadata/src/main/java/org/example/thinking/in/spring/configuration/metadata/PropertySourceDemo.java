package org.example.thinking.in.spring.configuration.metadata;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@PropertySource(value = "META-INF/users-config.properties")
public class PropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") long id, @Value("${user.name}") String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //可以在refresh前面自己添加propertySource
        Map<String, Object> map = new HashMap<>();
        map.put("user.name", "nimabi");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("my-propertysource",map);
        applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
        //注册当前类，作为Configuration
        applicationContext.register(PropertySourceDemo.class);

        applicationContext.refresh();

        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);

        for(Map.Entry<String,User> entry : beansOfType.entrySet()){
            System.out.printf("User bean name %s, content %s \n", entry.getKey(), entry.getValue());
        }
        System.out.println(applicationContext.getEnvironment().getPropertySources());
        applicationContext.close();
    }
}
