package org.example.thinking.in.spring.validation;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class ErrorsMessageDemo {

    public static void main(String[] args) {

        //创建pojo
        User user = new User();
        user.setName("llm");
        //1.选择Errors - BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(user, "user");
        //2.调用reject 或 rejectValue
        // reject 生成 ObjectError
        // reject 生成 FieldError
        errors.reject("user.properties.not.null");
        //user.name = user.getName()
        errors.rejectValue("name", "name.required");
        //3.获取Errors中ObjectError 和 FieldError
        //并且FiledError is ObjectError
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();

        //4.通过ObjetError 和 FieldError 中的code 和 args 来关联MessageSource实现
        MessageSource messageSource = createMessageSource();

        for(ObjectError error : allErrors){
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.CHINA);
            System.out.println(message);
        }

    }
    static MessageSource createMessageSource(){
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("name.required", Locale.CHINA, "the name of user must not be null");
        messageSource.addMessage("id.required", Locale.CHINA, "the id of user must not be null");
        messageSource.addMessage("user.properties.not.null", Locale.CHINA, "用户所有的属性不能为空");
        return messageSource;
    }
}
