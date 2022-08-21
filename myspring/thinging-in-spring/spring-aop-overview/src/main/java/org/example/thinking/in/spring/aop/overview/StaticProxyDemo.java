package org.example.thinking.in.spring.aop.overview;

public class StaticProxyDemo {
    public static void main(String[] args) {
        EchoService echoService = new EchoServiceProxy(new DefaultEchoService());
        System.out.println(echoService.echo("hello,world"));
    }
}
