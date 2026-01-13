package com.hli.design.zen.creational.singleton.v4_enum;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 皇帝类（枚举版 - 饿汉式max版本）
 *
 * 设计目的：
 * 1. 极其简洁：不需要写 private 构造，不需要写 getInstance。
 * 2. 天然防御：JVM 层面保证绝对无法被反射破坏。
 * 3. 序列化安全：自动处理序列化，不会生成新对象。
 *
 * 为什么需要该类：
 * 《Effective Java》推荐的最佳实践。
 * - 线程安全：JVM 保证枚举实例的创建是线程安全的。
 * - 防反射：Java 反射机制禁止通过 setAccessible(true) 创建枚举实例。
 * - 防序列化：枚举的序列化由 JVM 保证，不会生成新对象。
 */
@Getter
public enum EmperorEnum {
    
    // 没错，就这就行了！这就是唯一的皇帝。
    INSTANCE;

    // 可以有成员变量
    private String name;

    /**
     * 枚举的构造函数默认是私有的
     * JVM 保证只会调用一次
     */
    EmperorEnum() {
        // 📝 关键日志：应该只打印一次
        // 技巧：为了在枚举构造函数中使用 Logger，我们不能使用静态字段。
        // 我们可以临时获取一个 Logger 实例。
        Logger tempLog = LoggerFactory.getLogger(EmperorEnum.class);
        tempLog.info("皇帝(枚举版)登基了！|Emperor_Enum_created,hash={},thread={}", 
                System.identityHashCode(this), Thread.currentThread().getName());
        
        this.name = "朱元璋";
    }

    /**
     * 业务方法：审批圣旨
     * @param decree 圣旨内容
     */
    public void approveDecree(String decree) {
        // 业务方法中可以使用静态 Logger，或者继续使用 LoggerFactory 获取
        LoggerFactory.getLogger(EmperorEnum.class).info("皇帝审批了圣旨|Emperor_approved_decree,content={}", decree);
    }
}
