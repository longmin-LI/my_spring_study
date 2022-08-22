package org.example.thinking.in.spring.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * {@link ImportSelector}
 * @auhtor llm
 * @data 2022/8/23 0:00
 */

public class HelloWorldImportSelector implements ImportSelector{


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"org.example.thinking.in.spring.annotation.HelloWorldConfiguration"};
    }
}
