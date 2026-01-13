package com.hli.design.zen.creational.singleton.v3_static_inner;

import lombok.extern.slf4j.Slf4j;

/**
 * 皇帝类（静态内部类版 - 懒汉式max版）
 *
 * 设计目的：
 * 1. 演示利用 JVM 类加载机制实现单例。
 * 2. 实现懒加载（Lazy Loading）且无需显式同步（Lock-Free）。
 *
 * 为什么需要该类：
 * 这是比 DCL 更优雅的实现方式。它利用了 Java 语言规范中对类初始化阶段的线程安全保证，
 * 既避免了 synchronized 的性能开销，又保持了代码的简洁性。
 *
 * 核心实现思路：
 * - 外部类 EmperorStatic 加载时，不会触发 SingletonHolder 的加载。
 * - 只有当调用 getInstance() 时，JVM 才会加载 SingletonHolder 类。
 * - 静态属性 INSTANCE 在类加载初始化阶段被创建，JVM 保证了这个过程的线程安全性。
 */
@Slf4j
public class EmperorStatic {

    private EmperorStatic() {
        // 📝 关键日志：应该只打印一次，且是在第一次调用 getInstance 时打印
        log.info("皇帝(静态内部类版)登基了！|Emperor_Static_created,hash={},thread={}", 
                System.identityHashCode(this), Thread.currentThread().getName());
    }

    /**
     * 静态内部类
     * 只有在被调用时才会被加载，从而实现懒加载。
     */
    private static class SingletonHolder {
        // JVM 保证在类初始化阶段，静态变量的赋值是线程安全的
        private static final EmperorStatic INSTANCE = new EmperorStatic();
    }

    /**
     * 获取皇帝实例
     *
     * 实现逻辑：
     * 直接返回内部类的静态成员。
     * 这一步会触发 SingletonHolder 的类加载，进而初始化 INSTANCE。
     *
     * @return 皇帝实例
     */
    public static EmperorStatic getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
