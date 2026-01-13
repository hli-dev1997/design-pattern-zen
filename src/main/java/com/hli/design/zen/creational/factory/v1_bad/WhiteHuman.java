package com.hli.design.zen.creational.factory.v1_bad;

import lombok.extern.slf4j.Slf4j;

/**
 * 白人实现类
 *
 * 设计目的：
 * 1. 实现 Human 接口，提供白人的具体行为实现。
 * 2. 作为工厂模式中的具体产品之一。
 *
 * 核心实现思路：
 * - 实现 getColor() 方法，输出白色皮肤特征。
 * - 实现 talk() 方法，输出白人特有的语言或表达方式。
 */
@Slf4j
public class WhiteHuman implements Human {
    
    @Override
    public void getColor() {
        log.info("白人肤色|White_human_color,color=白色");
    }

    @Override
    public void talk() {
        log.info("白人说话|White_human_talk,message=白人会说话，一般都是单字节");
    }
}
