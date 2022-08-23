package org.example.thinking.in.spring.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author lilongmin
 * @date 2022/8/23 14:01
 * @description：偶数条件
 */
public class EvenCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //条件上下文
        Environment environment = context.getEnvironment();
        return environment.acceptsProfiles("even");
    }
}
