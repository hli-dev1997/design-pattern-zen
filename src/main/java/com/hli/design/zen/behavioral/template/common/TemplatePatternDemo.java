package com.hli.design.zen.behavioral.template.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板方法模式统一演示入口
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class TemplatePatternDemo {

    public static void main(String[] args) {
        log.info("=== 模板方法模式演示 ===\n");

        // V1: 劣质实现 (代码重复)
        com.hli.design.zen.behavioral.template.v1_basic.Demo.run();

        // V2: 标准模板方法实现
        com.hli.design.zen.behavioral.template.v2_standard.Demo.run();

        // V3: 进阶实现 (钩子方法)
        com.hli.design.zen.behavioral.template.v3_hook.Demo.run();
    }
}
