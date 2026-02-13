package com.hli.design.zen.behavioral.template.v1_basic;

import lombok.extern.slf4j.Slf4j;

/**
 * V1 劣质实现：代码大量重复
 *
 * <p>没有抽象出公共的流程，H1 和 H2 互相独立，逻辑冗余</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V1 劣质实现 (代码重复) ===");

        log.info("--- 制造 H1 (军用) ---");
        HummerH1 h1 = new HummerH1();
        h1.run();

        log.info("--- 制造 H2 (民用) ---");
        HummerH2 h2 = new HummerH2();
        h2.run();

        log.info("");
    }
}

/**
 * 军用悍马 H1
 *
 * <p>代码与 H2 大量重复</p>
 */
@Slf4j
class HummerH1 {

    public void run() {
        log.info("H1发动...");
        log.info("H1引擎声音：轰轰轰！");
        log.info("H1越野跑...");
        log.info("H1鸣笛...");
        log.info("H1停车...");
    }
}

/**
 * 民用悍马 H2
 */
@Slf4j
class HummerH2 {

    public void run() {
        log.info("H2发动...");
        log.info("H2引擎声音：吱吱吱...");
        log.info("H2公路上跑...");
        log.info("H2停车...");
    }
}
