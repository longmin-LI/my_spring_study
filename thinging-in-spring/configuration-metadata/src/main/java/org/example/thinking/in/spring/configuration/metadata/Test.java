package org.example.thinking.in.spring.configuration.metadata;

import java.util.concurrent.*;

public class Test {
    static class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println("nimabi");
        }
    }
    static class MyCallable implements Callable{

        @Override
        public Object call() throws Exception {
            System.out.println("call: caonima");
            return 1;
        }
    }
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
