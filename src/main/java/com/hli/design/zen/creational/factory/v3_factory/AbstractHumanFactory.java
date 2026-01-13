package com.hli.design.zen.creational.factory.v3_factory;

import com.hli.design.zen.creational.factory.common.Human;

/**
 * 抽象人类工厂（工厂方法模式的核心）
 *
 * 设计目的：
 * 1. 定义创建对象的接口，让子类决定实例化哪一个类。
 * 2. 符合开闭原则（OCP）：新增人种时，只需新增对应的具体工厂，无需修改抽象工厂。
 *
 * 为什么需要该类：
 * 相比简单工厂（Simple Factory），工厂方法模式将“创建谁”的逻辑下沉到了子类。
 * 这样，当需要扩展新产品时，不需要修改核心工厂代码，只需要扩展新的工厂子类即可。
 *
 * 核心实现思路：
 * - 定义一个抽象方法 createHuman()，返回 Human 接口。
 * - 具体的人种创建逻辑由子类（如 BlackHumanFactory）实现。
 */
public abstract class AbstractHumanFactory {
    
    /**
     * 创建人类的抽象方法
     * 
     * @return Human 具体的人类实例
     */
    public abstract Human createHuman();
}
