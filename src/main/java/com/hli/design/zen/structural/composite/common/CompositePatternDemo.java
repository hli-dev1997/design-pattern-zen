package com.hli.design.zen.structural.composite.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 组合模式统一演示入口
 *
 * <p>调用各版本的演示方法</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class CompositePatternDemo {

    public static void main(String[] args) {
        log.info("=== 组合模式演示 ===\n");

        // 透明模式
        com.hli.design.zen.structural.composite.v1_transparent.Demo.run();

        // 安全模式
        com.hli.design.zen.structural.composite.v2_safe.Demo.run();

        // 高级版本
        com.hli.design.zen.structural.composite.v3.Demo.run();
    }
}
