package com.hli.design.zen.structural.bridge.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 抽象化角色 (Abstraction): 消息抽象类
 *
 * <p>核心：持有 MessageSender 的引用，这就是"桥"</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractMessage {

    /** 桥接到实现层 */
    protected MessageSender messageSender;

    protected AbstractMessage(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
    public void sendMessage(String message) {
        this.messageSender.send(message);
    }
}

/**
 * 普通消息
 */
@Slf4j
class NormalMessage extends AbstractMessage {

    public NormalMessage(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void sendMessage(String message) {
        super.sendMessage(message);
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

/**
 * 短信发送器
 */
@Slf4j
class SmsSender implements MessageSender {

    @Override
    public void send(String message) {
        log.info("【SMS通道】发送: {}", message);
    }
}

/**
 * 邮件发送器
 */
@Slf4j
class EmailSender implements MessageSender {

    @Override
    public void send(String message) {
        log.info("【Email通道】发送: {}", message);
    }
}
