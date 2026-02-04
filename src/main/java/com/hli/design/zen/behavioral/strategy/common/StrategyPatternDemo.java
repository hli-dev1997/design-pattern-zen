package com.hli.design.zen.behavioral.strategy.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 策略模式统一演示入口
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class StrategyPatternDemo {

    public static void main(String[] args) {
        log.info("=== 策略模式演示 ===\n");

        // V1: 烂代码
        com.hli.design.zen.behavioral.strategy.v1_basic.Demo.run();

        // V2: 标准策略模式
        com.hli.design.zen.behavioral.strategy.v2_standard.Demo.run();

        // V3: 枚举策略
        com.hli.design.zen.behavioral.strategy.v3_enum.Demo.run();
    }
}
