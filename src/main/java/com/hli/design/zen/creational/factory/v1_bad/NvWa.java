package com.hli.design.zen.creational.factory.v1_bad;

import lombok.extern.slf4j.Slf4j;

/**
 * 女娲类（客户端 Client）
 *
 * 设计目的：
 * 1. 模拟场景中的调用者，演示如何使用工厂类创建对象。
 * 2. 验证简单工厂模式的功能。
 *
 * 为什么需要该类：
 * 在设计模式中，Client 是不可或缺的角色。它代表了使用模式的业务代码。
 * 通过 NvWa 类，我们可以直观地看到工厂模式如何简化了客户端的对象创建过程。
 *
 * 核心实现思路：
 * - 调用 HumanFactory.createHuman() 方法，传入不同的人种类型。
 * - 获取到 Human 对象后，调用其业务方法（getColor, talk）。
 */
@Slf4j
public class NvWa {

    /**
     * 女娲造人主程序
     *
     * 实现逻辑：
     * 1. 第一次造人：火候不足，造出了白人。
     * 2. 第二次造人：火候太过了，造出了黑人。
     * 3. 第三次造人：火候刚刚好，造出了黄种人。
     * 4. 每次造完人，都让他们展示一下肤色和说话能力。
     */
    public static void main(String[] args) {
        log.info("===== 女娲开始造人 (简单工厂模式) =====");

        // 1. 造白人
        log.info("--- 第一次造人：火候不足 ---");
        Human whiteHuman = HumanFactory.createHuman("white");
        if (whiteHuman != null) {
            whiteHuman.getColor();
            whiteHuman.talk();
        }

        // 2. 造黑人
        log.info("--- 第二次造人：火候太过了 ---");
        Human blackHuman = HumanFactory.createHuman("black");
        if (blackHuman != null) {
            blackHuman.getColor();
            blackHuman.talk();
        }

        // 3. 造黄种人
        log.info("--- 第三次造人：火候刚刚好 ---");
        Human yellowHuman = HumanFactory.createHuman("yellow");
        if (yellowHuman != null) {
            yellowHuman.getColor();
            yellowHuman.talk();
        }
        
        // 4. 测试未知类型
        log.info("--- 第四次造人：随便捏捏 ---");
        Human unknownHuman = HumanFactory.createHuman("blue");
        if (unknownHuman == null) {
            log.warn("造人失败，没有这种人种|Creation_failed,type=blue");
        }

        log.info("===== 女娲造人结束 =====");
    }
}
