package com.hli.design.zen.structural.bridge.v1_basic;

import lombok.extern.slf4j.Slf4j;

/**
 * V1 烂代码演示：类爆炸问题
 *
 * <p>不使用桥接模式，导致 类数量 = 消息类型 × 发送渠道</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V1 烂代码（类爆炸） ===");
        log.info("问题：短信普通/加急 + 邮件普通/加急 = 4个类");
        log.info("新增App推送？再加2个类...");
        log.info("新增特急？再加3个类...");
        log.info("类数量 = 消息类型 × 发送渠道 = 指数爆炸！\n");

        // 演示烂代码
        new SmsNormalMessage().send();
        new SmsUrgentMessage().send();
        new EmailNormalMessage().send();
        new EmailUrgentMessage().send();
        log.info("");
    }
}

// ========== 以下是"烂代码"示例 ==========

class SmsNormalMessage {
    public void send() {
        System.out.println("发送普通短信");
    }
}

class SmsUrgentMessage {
    public void send() {
        System.out.println("发送加急短信");
    }
}

class EmailNormalMessage {
    public void send() {
        System.out.println("发送普通邮件");
    }
}

class EmailUrgentMessage {
    public void send() {
        System.out.println("发送加急邮件");
    }
}
// 还没完...如果来了个App推送，你得再加两个类...
// 如果再来个"特急"，你还得再加3个类...
