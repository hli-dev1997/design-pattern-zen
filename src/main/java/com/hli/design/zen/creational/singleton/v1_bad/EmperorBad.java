package com.hli.design.zen.creational.singleton.v1_bad;

import lombok.extern.slf4j.Slf4j;

/**
 * 皇帝类（烂代码版）
 *
 * 设计目的：
 * 1. 演示懒汉式单例模式的实现。
 * 2. 暴露多线程环境下线程不安全的问题。
 *
 * 为什么需要该类：
 * 作为反面教材，展示在并发场景下，简单的判空逻辑会导致创建多个实例，破坏单例约束。
 *
 * 核心实现思路：
 * - 私有化构造函数，防止外部直接实例化。
 * - 提供静态方法 getInstance() 获取实例。
 * - 在 getInstance() 中进行判空，如果为空则创建实例。
 */
@Slf4j
public class EmperorBad {
    private static EmperorBad instance;
    
    // 构造私有化，不准外面随便生
    private EmperorBad() {
        // 📝 关键日志：如果控制台打印多次，说明单例模式失效了！
        log.info("皇帝(烂代码版)驾崩了...不对，是诞生了！|Emperor_Bad_created,hash={},thread={}", 
                System.identityHashCode(this), Thread.currentThread().getName());
    } 

    /**
     * 获取皇帝实例
     *
     * 实现逻辑：
     * 1. 检查 instance 是否为 null。
     * 2. 如果为 null，则模拟耗时操作（放大并发问题），然后创建新实例。
     * 3. 返回 instance。
     *
     * @return 皇帝实例
     */
    public static EmperorBad getInstance() {
        if (instance == null) {
            // 💀 致命死穴：线程A刚走到这，线程B也进来了，结果生了两个皇帝！
            log.info("致命死穴_线程闯入|Fatal_race_condition,thread={}", Thread.currentThread().getName());
            try {
                // 模拟初始化耗时，放大并发问题
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error("线程休眠异常|Thread_sleep_error", e);
            }
            instance = new EmperorBad();
        }
        return instance;
    }
}
