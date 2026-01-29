package com.hli.design.zen.structural.proxy.v2_static;

import com.hli.design.zen.structural.proxy.common.SmsService;

/**
 * @author hli
 * @program: design-pattern-zen
 * @Date 2026-01-29 20:41:42
 * @description: 真实主题(只干活不谈钱)
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public void send(String msg) {
        System.out.println("✅ (核心) 发送短信内容: " + msg);
    }
}
