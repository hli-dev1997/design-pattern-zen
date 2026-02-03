package com.hli.design.zen.structural.flyweight.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 享元模式统一演示入口
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class FlyweightPatternDemo {

    public static void main(String[] args) {
        log.info("=== 享元模式演示 ===\n");

        com.hli.design.zen.structural.flyweight.v1_simple.Demo.run();
        com.hli.design.zen.structural.flyweight.v2_composite.Demo.run();
        com.hli.design.zen.structural.flyweight.v3.Demo.run();
    }
}
