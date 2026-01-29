package com.hli.design.zen.structural.proxy.v1_bad;

import com.hli.design.zen.structural.proxy.common.SmsService;

/**
 * @author hli
 * @program: design-pattern-zen
 * @Date 2026-01-29 20:39:18
 * @description: 实现类
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public void send(String msg) {
        // ❌ 烂代码：计费逻辑混入了业务代码
        System.out.println("💰 扣费 0.1 元");
        System.out.println("正在发送短信：" + msg);//核心业务
    }
}
