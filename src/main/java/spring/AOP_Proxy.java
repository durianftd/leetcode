package spring;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AOP_Proxy implements InvocationHandler {

    Object o;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = null;
        try {
            System.out.println("开始执行");
            invoke = method.invoke(o, args);
            System.out.println("结束执行");
        } catch (Exception e) {
            System.out.println("异常哈哈哈");
        }
        return invoke;
    }

    public Object getInstance(Object o) {
        this.o = o;
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), this);
    }
}
