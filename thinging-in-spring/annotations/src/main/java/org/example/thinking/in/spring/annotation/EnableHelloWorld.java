package org.example.thinking.in.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @auhtor llm
 * @data 2022/8/22 23:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class)   //第二部 通过import注解导入具体实现
//@Import(HelloWorldImportSelector.class)//方式二：通过ImportSelector接口实现
@Import(HelloWorldBeanDefinitionRegister.class)//方法三：通过importBeanDefinitionRegister来实现
public @interface EnableHelloWorld {//第一步：通过@Enable*命名
}
