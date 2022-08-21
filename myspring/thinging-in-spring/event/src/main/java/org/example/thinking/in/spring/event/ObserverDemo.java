package org.example.thinking.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * @auhtor llm
 * @data 2022/8/13 23:08
 */
public class ObserverDemo {

    public static void main(String[] args) {

        EventObservable observable = new EventObservable();
        //添加观察者
        observable.addObserver(new EventObserver());
        //发布消息（事件）
        observable.notifyObservers("hello,world");

    }
    static class EventObservable extends Observable{
        //因为这个方法是protected，所以我们需要自己实现一下，不然如果不改变状态的话，我们接收不到msg
        @Override
        public void setChanged(){
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }
    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println("收到事件：" + eventObject);
        }
    }
}
