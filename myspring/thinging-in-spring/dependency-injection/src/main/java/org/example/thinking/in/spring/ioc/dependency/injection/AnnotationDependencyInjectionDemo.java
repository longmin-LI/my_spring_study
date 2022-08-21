package org.example.thinking.in.spring.ioc.dependency.injection;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationDependencyInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源，解析并且生成BeanDefinitions
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        //依赖查找，并且创建Bean,直接这样的话，会没办法初始化，因为找不到user这个bean，前面是通过导入了XML文件的方式才可以
        //所以加上上面的那些代码，这也就说明，我们在使用注解配置的时候，也可以搭配XML文件一起使用
        UserHolder bean = applicationContext.getBean(UserHolder.class);
        System.out.println(bean);
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
