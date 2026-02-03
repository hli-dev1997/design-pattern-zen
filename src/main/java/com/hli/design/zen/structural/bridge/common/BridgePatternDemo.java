package com.hli.design.zen.structural.bridge.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 桥接模式统一演示入口
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class BridgePatternDemo {

    public static void main(String[] args) {
        log.info("=== 桥接模式演示 ===\n");

        // V1: 烂代码
        com.hli.design.zen.structural.bridge.v1_basic.Demo.run();

        // V2: 桥接模式
        com.hli.design.zen.structural.bridge.v2_multi.Demo.run();

        // V3: 扩展演示
        com.hli.design.zen.structural.bridge.v3_bean.Demo.run();
    }
}
