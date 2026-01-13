package com.hli.design.zen.creational.factory.v3_factory;

import com.hli.design.zen.creational.factory.common.Human;
import com.hli.design.zen.creational.factory.common.WhiteHuman;

/**
 * 白人工厂（具体工厂）
 *
 * 设计目的：
 * 1. 实现 AbstractHumanFactory 接口，专门负责创建白人实例。
 * 2. 封装白人对象的创建细节。
 *
 * 为什么需要该类：
 * 符合单一职责原则。当需要修改白人的创建逻辑时，只需要修改这个类，不会影响到其他工厂。
 *
 * 核心实现思路：
 * - 直接实例化 WhiteHuman 对象并返回。
 */
public class WhiteHumanFactory extends AbstractHumanFactory {

    @Override
    public Human createHuman() {
        return new WhiteHuman();
    }
}
