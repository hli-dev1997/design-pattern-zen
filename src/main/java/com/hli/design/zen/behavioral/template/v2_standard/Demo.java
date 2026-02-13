package com.hli.design.zen.behavioral.template.v2_standard;

import lombok.extern.slf4j.Slf4j;

/**
 * V2 标准模板方法实现
 *
 * <p>核心：流程由父类控制，细节由子类实现</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V2 标准模板方法实现 ===");

        log.info("--- 制造 H1 (军用) ---");
        AbstractHummerModel h1 = new HummerH1();
        h1.run();

        log.info("--- 制造 H2 (民用) ---");
        AbstractHummerModel h2 = new HummerH2();
        h2.run();

        log.info("");
    }
}

/**
 * 抽象模板类 (悍马模型)
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
abstract class AbstractHummerModel {

    /**
     * 核心模板方法 (Template Method)
     *
     * <p>定义算法骨架，加 final 防止子类破坏流程</p>
     */
    public final void run() {
        start();
        engineBoom();
        runLogic();
        alarm();
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
}

/**
 * 具体实现：H1 (军用悍马)
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
 */
@Slf4j
class HummerH2 extends AbstractHummerModel {

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
        // H2 默认还是响一下，但不改流程
        log.info("H2鸣笛：滴滴...");
    }

    @Override
    protected void stop() {
        log.info("H2刹车...");
    }
}
