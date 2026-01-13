package com.hli.design.zen.creational.factory.v1_bad;

import com.hli.design.zen.creational.factory.common.Human;
import lombok.extern.slf4j.Slf4j;

/**
 * 蓝人实现类（阿凡达？）
 *
 * 设计目的：
 * 1. 演示简单工厂模式的扩展性问题。
 * 2. 当新增一个人种时，除了新增这个类，还必须修改 HumanFactory 的 switch-case 逻辑。
 *
 * 为什么需要该类：
 * 用于验证“开闭原则”的违背。在简单工厂模式下，新增产品必须修改工厂代码。
 *
 * 核心实现思路：
 * - 实现 getColor() 方法，输出蓝色皮肤特征。
 * - 实现 talk() 方法，输出蓝人特有的语言。
 */
@Slf4j
public class BlueHuman implements Human {
    
    @Override
    public void getColor() {
        log.info("蓝人肤色|Blue_human_color,color=蓝色");
    }

    @Override
    public void talk() {
        log.info("蓝人说话|Blue_human_talk,message=蓝人会说话，可能是纳美语");
    }
}
