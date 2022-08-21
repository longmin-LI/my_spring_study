package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Import(AnnotationBeanDefinitionDemo.Config.class)//3.通过@Import进行导入
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class配置类
        applicationContext.register(Config.class);
        //通过BeanDefinition 注册API实现
        //1.命名Bean的注册方式
        registerBeanDefinition(applicationContext,"llm-papa");
        //2.非命名Bean的注册方式
        registerBeanDefinition(applicationContext);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找
        Map<String, Config> configBeans = applicationContext.getBeansOfType(Config.class);
        System.out.println("Config Bean的所有Bean" + configBeans);
        System.out.println("User Bean的所有Bean" + applicationContext.getBeansOfType(User.class));
        //显式的关闭Spring上下文
        applicationContext.close();
    }

    /**
     * 有命名bean的注册方式
     * @param registry
     * @param beanName
     */
    //通过java原生API来实现
    public static void registerBeanDefinition(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id",1)
                .addPropertyValue("name","llm");
        //判断一下beanName存在不存在，进行下面的逻辑处理
        if(StringUtils.hasText(beanName)){
            //命名方式注册BeanDefinition
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else{
            //非命名方式注册BeanDefinition
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry){
        registerBeanDefinition(registry,null);
    }
    //2.通过@Component
    //定义当前类作为Spring Bean组件
    @Component
    public static class Config{
        //1.通过@Bean的方式
        @Bean(name = {"user","llm-user"})
        public User user(){
            User user = new User();
            user.setId(2L);
            user.setName("llm");
            return user;
        }
    }
}
