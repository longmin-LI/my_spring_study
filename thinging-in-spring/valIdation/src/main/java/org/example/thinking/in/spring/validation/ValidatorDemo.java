package org.example.thinking.in.spring.validation;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.Locale;

import static org.example.thinking.in.spring.validation.ErrorsMessageDemo.createMessageSource;

/**
 * {@link org.springframework.validation.Validator}
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        //1.创建Validator
        Validator validator = new UserValidator();
        //2.判断是否支持目标对象的校验
        User user = new User();

        boolean supports = validator.supports(user.getClass());
        System.out.println("是否支持该类型的校验" + supports);

        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user,errors);
        //获取MessageSource对象
        MessageSource messageSource = createMessageSource();
        for(ObjectError error : errors.getAllErrors()){
            messageSource.getMessage(error.getCode(), error.getArguments(), Locale.CHINA);
            System.out.println(messageSource);
        }
    }

    static class UserValidator implements Validator{

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "name.required");
        }
    }
}
