package com.hli.design.zen.structural.bridge.v3_bean;

import lombok.extern.slf4j.Slf4j;

/**
 * V3 扩展演示：新增渠道只需实现接口
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V3 扩展演示 ===");
        log.info("新增App推送渠道？只需实现 MessageSender 接口！");

        // 模拟新增 App 推送（Lambda 快速实现）
        MessageSender appPush = message -> log.info("【App推送】发送: {}", message);

        // 无需修改任何消息类，直接组合
        AbstractMessage urgentPush = new UrgentMessage(appPush);
        urgentPush.sendMessage("紧急通知！");

        log.info("\n桥接模式优势：抽象和实现可独立扩展");
        log.info("类数量 = M + N（而非 M × N）");
    }
}

/** 实现化角色接口 */
interface MessageSender {
    void send(String message);
}

/** 抽象化角色 */
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

/** 加急消息 */
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
