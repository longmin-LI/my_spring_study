package org.example.thinking.in.spring.environment;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lilongmin
 * @date 2022/8/23 15:04
 * @description
 */
public class PropertyPlaceHolderConfigurationDemo {

    public static void main(String[] args) {
        //1.创建并且启动spring上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/placeholders-resolver.xml");

        User user = context.getBean("user", User.class);
        //如果没有处理占位符直接输出的话，会出错，因为无法转换
        System.out.println(user);

        context.close();
    }
}
