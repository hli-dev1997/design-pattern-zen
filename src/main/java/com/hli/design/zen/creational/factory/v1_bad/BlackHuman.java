package com.hli.design.zen.creational.factory.v1_bad;

import lombok.extern.slf4j.Slf4j;

/**
 * 黑人实现类
 *
 * 设计目的：
 * 1. 实现 Human 接口，提供黑人的具体行为实现。
 * 2. 作为工厂模式中的具体产品之一。
 *
 * 核心实现思路：
 * - 实现 getColor() 方法，输出黑色皮肤特征。
 * - 实现 talk() 方法，输出黑人特有的语言或表达方式。
 */
@Slf4j
public class BlackHuman implements Human {
    
    @Override
    public void getColor() {
        log.info("黑人肤色|Black_human_color,color=黑色");
    }

    @Override
    public void talk() {
        log.info("黑人说话|Black_human_talk,message=黑人会说话，一般人听不懂");
    }
}
