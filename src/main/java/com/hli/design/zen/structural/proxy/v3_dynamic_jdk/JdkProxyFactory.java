package com.hli.design.zen.structural.proxy.v3_dynamic_jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author hli
 * @program: design-pattern-zen
 * @Date 2026-01-29 20:45:36
 * @description: 通用动态代理处理器
 */
public class JdkProxyFactory implements InvocationHandler {
    private final Object target;//它可以代理任何对象(Object)

    public JdkProxyFactory(Object target) {
        this.target = target;
    }

    // 🔥 核心：获取代理对象 (JDK会在内存中动态生成字节码)
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//类加载器
                target.getClass().getInterfaces(),//只要是接口都能代理
                new JdkProxyFactory(target)       //处理器
        );
    }

    //🔥 核心：所有方法调用都会进入这里
    @Override
    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable {
        System.out.println("🔥 [动态代理] 开始事务/日志/权限检查...");
        //反射调用真实对象的方法
        Object result = method.invoke(target, args);
        System.out.println("🔥 [动态代理] 提交事务/清理资源...");
        return result;
    }
}
// Client:
// SmsService proxy = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
// proxy.send("动态代理太强了");