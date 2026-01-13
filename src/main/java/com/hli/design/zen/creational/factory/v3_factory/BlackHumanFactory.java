package com.hli.design.zen.creational.factory.v3_factory;

import com.hli.design.zen.creational.factory.common.BlackHuman;
import com.hli.design.zen.creational.factory.common.Human;

/**
 * 黑人工厂（具体工厂）
 *
 * 设计目的：
 * 1. 实现 AbstractHumanFactory 接口，专门负责创建黑人实例。
 * 2. 封装黑人对象的创建细节（如初始化参数、属性设置等）。
 *
 * 为什么需要该类：
 * 将“创建黑人”的职责单一化。如果黑人的创建过程很复杂（比如需要查数据库、设置默认值），
 * 这些逻辑都应该封装在这里，而不是散落在客户端代码中。
 *
 * 核心实现思路：
 * - 实例化 BlackHuman 对象。
 * - (可选) 进行必要的初始化操作，如设置语言。
 * - 返回 Human 接口。
 */
public class BlackHumanFactory extends AbstractHumanFactory {
    
    @Override
    public Human createHuman() {
        // 1. 生产对象
        BlackHuman human = new BlackHuman();
        // 2. (可选) 如果有必要的初始化，写在这里
         human.language("非洲语");
        // 3. 返回成品
        return human;
    }
}
