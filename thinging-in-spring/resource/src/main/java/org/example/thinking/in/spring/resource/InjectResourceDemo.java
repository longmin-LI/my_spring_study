package org.example.thinking.in.spring.resource;

import org.example.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * {@link org.springframework.core.io.Resource}
 */
public class InjectResourceDemo {
    //@Value注解注入Resource对象
    @Value("classpath:/MATE-INF/default.properties")
    private Resource resource;
    //注入集合的Resource
    @Value("classpath:/MATE-INF/*.properties")
    private Resource[] resources;

    //@Value注入外部化配置
    @Value("${user.dir}")
    private String currentProjectRootPath;
    @PostConstruct
    public void init(){
        System.out.println(ResourceUtils.getContent(resource));
        System.out.println("===========");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println(currentProjectRootPath);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(InjectResourceDemo.class);

        context.refresh();

        context.close();
    }
}
