package com.hli.design.zen.creational.factory.v1_bad;

import lombok.extern.slf4j.Slf4j;

/**
 * 黄种人实现类
 *
 * 设计目的：
 * 1. 实现 Human 接口，提供黄种人的具体行为实现。
 * 2. 作为工厂模式中的具体产品之一。
 *
 * 核心实现思路：
 * - 实现 getColor() 方法，输出黄色皮肤特征。
 * - 实现 talk() 方法，输出黄种人特有的语言或表达方式。
 */
@Slf4j
public class YellowHuman implements Human {
    
    @Override
    public void getColor() {
        log.info("黄种人肤色|Yellow_human_color,color=黄色");
    }

    @Override
    public void talk() {
        log.info("黄种人说话|Yellow_human_talk,message=黄种人会说话，一般都是双字节");
    }
}
