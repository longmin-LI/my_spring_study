package org.example.thinking.in.spring.aop.overview;


public class ClassLoaderTestDemo {

    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);

    }
}
