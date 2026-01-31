package com.hli.design.zen.structural.decorator.v2_standard;

import com.hli.design.zen.structural.decorator.common.Drink;

/**
 * 黑咖啡 - 具体组件 (ConcreteComponent)
 *
 * <p>装饰器模式中被装饰的原始对象，提供基础功能实现。
 * 装饰器将在此基础上动态添加额外职责。</p>
 *
 * <p><b>角色说明：</b></p>
 * <ul>
 *     <li>实现 {@link Drink} 接口，提供基础的价格和描述</li>
 *     <li>作为装饰器链的起点（最内层对象）</li>
 * </ul>
 *
 * @author hli
 * @since 2026-01-30
 * @see Drink
 * @see CoffeeDecorator
 */
public class BlackCoffee implements Drink {

    /**
     * 黑咖啡基础价格
     */
    private static final double BASE_PRICE = 10.0D;

    /**
     * {@inheritDoc}
     *
     * @return 黑咖啡基础价格 10.0
     */
    @Override
    public double cost() {
        return BASE_PRICE;
    }

    /**
     * {@inheritDoc}
     *
     * @return 饮品描述 "黑咖啡"
     */
    @Override
    public String desc() {
        return "黑咖啡";
    }
}
