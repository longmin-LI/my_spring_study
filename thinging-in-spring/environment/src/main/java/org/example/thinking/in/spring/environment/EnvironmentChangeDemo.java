package org.example.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置属性源变更
 * @auhtor llm
 * @data 2022/8/23 20:42
 */
public class EnvironmentChangeDemo {

    @Value("${user.name}")
    private String name;
    //PropertySource("first-property-source) {user.name = 李龙敏}
    //PropertySource(Java System Properties) {user.name = Li longmin}

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(EnvironmentChangeDemo.class);
        //在spring应用上下文启动前进行调整
        ConfigurableEnvironment environment = context.getEnvironment();
        //获取MutablePropertySource 对象
        MutablePropertySources propertySources = environment.getPropertySources();
        //可以动态的插入PropertySource到PropertySources中
        Map<String, Object> source = new HashMap<>();
        source.put("user.name", "李龙敏");
        PropertySource propertySource = new MapPropertySource("first-property-source",source);
        //插入到了第一位
        propertySources.addFirst(propertySource);

        context.refresh();
        //启动后来修改，但是不具备更新能力，Spring配置进去的还是上面的值
        source.put("user.name", "nimabi");
        EnvironmentChangeDemo bean = context.getBean(EnvironmentChangeDemo.class);

        System.out.println(bean.name);

        for(PropertySource ps : propertySources){
            System.out.printf("PropertySource: %s 'user.name' 属性: %s \n",ps.getName(), ps.getProperty("user.name"));
        }
        context.close();
    }
}
