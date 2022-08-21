package org.example.thinking.in.spring.aop.overview;

public class EchoServiceProxy implements EchoService{

    private EchoService echoService;

    public EchoServiceProxy(EchoService echoService){
        this.echoService = echoService;
    }

    @Override
    public String echo(String msg) {
        long start = System.currentTimeMillis();
        echoService.echo(msg);
        long end = System.currentTimeMillis();
        System.out.println(end-start + msg);
        return msg;
    }
}
