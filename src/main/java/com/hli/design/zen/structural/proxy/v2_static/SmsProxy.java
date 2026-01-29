package com.hli.design.zen.structural.proxy.v2_static;

import com.hli.design.zen.structural.proxy.common.SmsService;

/**
 * @author hli
 * @program: design-pattern-zen
 * @Date 2026-01-29 20:42:34
 * @description: 静态代理(经纪人)
 */
public class SmsProxy implements SmsService {
    //持有真实对象的引用
    private final SmsService target;

    public SmsProxy(SmsService target) {
        this.target = target;
    }

    @Override
    public void send(String msg) {
        System.out.println("💰 (增强) 前置收费 0.1 元"); // 前置增强
        target.send(msg);
        System.out.println("📝 (增强) 后置记录日志");     // 后置增强
    }
}
// Client: new SmsProxy(new SmsServiceImpl()).send("Hello");