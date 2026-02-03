package com.hli.design.zen.structural.bridge.common;

/**
 * 实现化角色 (Implementor): 消息发送渠道接口
 *
 * <p>这是桥接模式的"实现"维度，定义发送消息的接口</p>
 *
 * @author hli
 * @since 1.0.0
 */
public interface MessageSender {

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
    void send(String message);
}
