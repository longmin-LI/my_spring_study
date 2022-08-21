package org.example.thinking.in.spring.ioc.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalScope implements Scope {
    public static final String SCOPE_NAME = "thread_local";

    private final NamedThreadLocal<Map<String,Object>> threadLocal = new NamedThreadLocal("thread_local_scope"){
        @Override
        public Map<String,Object> initialValue(){
            return new HashMap<>();
        }
    };
    //这个get方法是给容器去调用的
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String,Object> context = getContext();

        Object object = context.get(name);

        if(object == null) {
            object = objectFactory.getObject();
            context.put(name,object);
        }
        return object;
    }

    private Map<String, Object> getContext() {
        return threadLocal.get();
    }

    @Override
    public Object remove(String name) {
        Map<String,Object> context = getContext();

        return context.remove(name);

    }
    //销毁的一个回调函数
    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //TODO
    }
    @Override
    public Object resolveContextualObject(String key) {
        Map<String,Object> context = getContext();
        return context.get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }
}
