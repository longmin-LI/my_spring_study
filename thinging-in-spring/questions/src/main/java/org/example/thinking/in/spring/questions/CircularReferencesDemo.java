package org.example.thinking.in.spring.questions;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @auhtor llm
 * @data 2022/8/25 15:27
 */
public class CircularReferencesDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(CircularReferencesDemo.class);

        context.refresh();
        System.out.println(context.getBean(Student.class));
        System.out.println(context.getBean(ClassRoom.class));
        context.close();
    }

    @Bean
    public static Student student(){
        Student student = new Student();
        student.setId(1L);
        student.setName("张三");
        return student;
    }

    @Bean
    public static ClassRoom classRoom(){
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("C122");
        return classRoom;
    }
}
