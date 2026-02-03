package com.hli.design.zen.structural.bridge.v2_multi;

import lombok.extern.slf4j.Slf4j;

/**
 * V2 桥接模式演示：抽象与实现分离
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V2 桥接模式 ===");

        // 创建发送器（实现维度）
        MessageSender smsSender = new SmsSender();
        MessageSender emailSender = new EmailSender();

        // 创建消息（抽象维度）+ 桥接发送器
        AbstractMessage normalSms = new NormalMessage(smsSender);
        AbstractMessage urgentEmail = new UrgentMessage(emailSender);

        // 发送
        normalSms.sendMessage("您的验证码是 123456");
        urgentEmail.sendMessage("服务器CPU 100%！");
        log.info("");
    }
}

/**
 * 实现化角色 (Implementor): 发送渠道接口
 */
interface MessageSender {
    void send(String message);
}

/**
 * 具体实现：短信
 */
@Slf4j
class SmsSender implements MessageSender {
    @Override
    public void send(String message) {
        log.info("【SMS通道】发送: {}", message);
    }
}

/**
 * 具体实现：邮件
 */
@Slf4j
class EmailSender implements MessageSender {
    @Override
    public void send(String message) {
        log.info("【Email通道】发送: {}", message);
    }
}

/**
 * 抽象化角色 (Abstraction): 消息抽象类
 *
 * <p>核心：持有 MessageSender 的引用，这就是"桥"</p>
 */
@Slf4j
abstract class AbstractMessage {
    protected MessageSender messageSender;

    protected AbstractMessage(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String message) {
        this.messageSender.send(message);
    }
}

/**
 * 普通消息
 */
class NormalMessage extends AbstractMessage {
    public NormalMessage(MessageSender messageSender) {
        super(messageSender);
    }
}

/**
 * 加急消息
 */
@Slf4j
class UrgentMessage extends AbstractMessage {
    public UrgentMessage(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void sendMessage(String message) {
        message = "【加急】" + message;
        super.sendMessage(message);
        log.info(">> 监控：发送了一条加急消息");
    }
}
