package com.hli.design.zen.creational.factory.v1_bad;

/**
 * 人类接口
 *
 * 设计目的：
 * 1. 定义人类的通用行为，作为所有具体人种的抽象父类。
 * 2. 符合依赖倒置原则（DIP），高层模块应依赖于抽象。
 *
 * 为什么需要该类：
 * 在工厂模式中，工厂返回的是抽象产品（Human），而不是具体产品（如 YellowHuman）。
 * 这样调用者只需要关心 Human 接口，而不需要关心具体的实现类。
 */
public interface Human {

    /**
     * 获取肤色
     * 每个具体人种都有自己的肤色实现
     */
    void getColor();

    /**
     * 说话
     * 人类都会说话，但语言可能不同
     */
    void talk();
}
