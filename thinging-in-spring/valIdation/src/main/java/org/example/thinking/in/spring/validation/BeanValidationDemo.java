package org.example.thinking.in.spring.validation;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * @see org.springframework.validation.Validator
 * @see org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
 */
public class BeanValidationDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-validation-context.xml");
        UserProcessor bean = context.getBean(UserProcessor.class);

        bean.process(new User());
        context.close();
    }
    @Component
    @Validated
    static class UserProcessor{

        public void process(@Valid User user){
            System.out.println(user);
        }
    }

    static class User{
        @NotNull
        private int id;

        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
