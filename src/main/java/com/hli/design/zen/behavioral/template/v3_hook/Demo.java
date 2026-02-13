package com.hli.design.zen.behavioral.template.v3_hook;

import lombok.extern.slf4j.Slf4j;

/**
 * V3 进阶实现：钩子方法 (Hook Method)
 *
 * <p>核心：子类通过钩子反向控制父类流程</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V3 进阶实现 (钩子方法) ===");

        log.info("--- 制造 H1 (军用) ---");
        AbstractHummerModel h1 = new HummerH1();
        h1.run(); // H1 流程固定，肯定会鸣笛

        log.info("--- 制造 H2 (民用) ---");
        HummerH2 h2 = new HummerH2();
        // 模拟导演/车主：H2 这次悄悄进村，别吵
        h2.setAlarm(false);
        h2.run();

        log.info("");
    }
}

/**
 * 带有钩子的抽象模板类
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
abstract class AbstractHummerModel {

    /**
     * 核心模板方法 (Template Method)
     */
    public final void run() {
        start();
        engineBoom();
        runLogic();

        // 🎣 钩子判断：由子类决定是否执行此步骤
        if (isAlarm()) {
            alarm();
        }

        stop();
    }

    /**
     * 车子发动
     */
    protected abstract void start();

    /**
     * 引擎轰鸣响声
     */
    protected abstract void engineBoom();

    /**
     * 车子运行逻辑
     */
    protected abstract void runLogic();

    /**
     * 鸣笛喇叭声
     */
    protected abstract void alarm();

    /**
     * 停车
     */
    protected abstract void stop();

    /**
     * 钩子方法 (Hook Method)
     *
     * <p>默认返回 true，子类可以按需重写</p>
     *
     * @return 是否鸣笛
     */
    protected boolean isAlarm() {
        return true;
    }
}

/**
 * 具体实现：H1 (军用悍马)
 *
 * <p>剧情：喇叭必须响，使用默认钩子即可</p>
 */
@Slf4j
class HummerH1 extends AbstractHummerModel {

    @Override
    protected void start() {
        log.info("H1发动...");
    }

    @Override
    protected void engineBoom() {
        log.info("H1引擎声音：轰轰轰！");
    }

    @Override
    protected void runLogic() {
        log.info("H1越野奔跑...");
    }

    @Override
    protected void alarm() {
        log.info("H1鸣笛：嘟嘟嘟！");
    }

    @Override
    protected void stop() {
        log.info("H1停车...");
    }
}

/**
 * 具体实现：H2 (民用悍马)
 *
 * <p>剧情：引擎“吱吱吱”，且允许控制是否鸣笛</p>
 */
@Slf4j
class HummerH2 extends AbstractHummerModel {

    /**
     * 使用 ThreadLocal 解决单例状态下的线程安全隐患
     *
     * <p>强制 [Concurrency]：在并发场景中，单例 Bean 严禁使用非线程安全的成员变量</p>
     */
    private static final ThreadLocal<Boolean> ALARM_THREAD_LOCAL = ThreadLocal.withInitial(() -> true);

    /**
     * 设置是否鸣笛的开关 (子类特有方法)
     *
     * @param isAlarm 是否鸣笛
     */
    public void setAlarm(boolean isAlarm) {
        ALARM_THREAD_LOCAL.set(isAlarm);
    }

    /**
     * 🔥 重写钩子：反向控制父类流程
     *
     * @return 当前线程的鸣笛设置
     */
    @Override
    protected boolean isAlarm() {
        return ALARM_THREAD_LOCAL.get();
    }

    @Override
    protected void start() {
        log.info("H2平稳发动...");
    }

    @Override
    protected void engineBoom() {
        log.info("H2引擎声音：吱吱吱...");
    }

    @Override
    protected void runLogic() {
        log.info("H2马路上跑...");
    }

    @Override
    protected void alarm() {
        log.info("H2鸣笛：滴滴...");
    }

    @Override
    protected void stop() {
        log.info("H2刹车...");
    }
}
