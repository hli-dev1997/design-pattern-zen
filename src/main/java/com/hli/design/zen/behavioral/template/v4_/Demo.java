package com.hli.design.zen.behavioral.template.v4_;

import lombok.extern.slf4j.Slf4j;

/**
 * V4 包心实现：钩子方法 + 默认空实现
 *
 * <p>核心：通过钩子方法控制流程分支，通过默认空实现避免子类被迫重写无关方法</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    /**
     * 运行 V4 演示
     */
    public static void run() {
        log.info("=== V4 包心实现 (钩子 + 默认空实现) ===");

        log.info("--- 校招流程 (含 HR 面) ---");
        AbstractGetOfferModel school = new SchoolOffer();
        school.run();

        log.info("--- 社招流程 (跳过 HR 面) ---");
        AbstractGetOfferModel social = new SocialOffer();
        social.run();

        log.info("");
    }
}
