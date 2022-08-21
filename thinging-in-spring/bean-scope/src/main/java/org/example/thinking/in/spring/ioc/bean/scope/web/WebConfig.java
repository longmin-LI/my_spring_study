package org.example.thinking.in.spring.ioc.bean.scope.web;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {

    @Bean
//    @RequestScope
    @ApplicationScope
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("你爹李龙敏");
        return user;
    }
}
