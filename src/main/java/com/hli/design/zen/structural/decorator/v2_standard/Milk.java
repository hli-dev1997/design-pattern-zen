package com.hli.design.zen.structural.decorator.v2_standard;

import com.hli.design.zen.structural.decorator.common.Drink;

/**
 * 牛奶装饰器 - 具体装饰器 (ConcreteDecorator)
 *
 * <p>为饮品添加牛奶配料，增加价格并修改描述。</p>
 *
 * <p><b>功能增强：</b></p>
 * <ul>
 *     <li>价格 +2.0</li>
 *     <li>描述追加 " + 奶"</li>
 * </ul>
 *
 * @author hli
 * @since 2026-01-30
 * @see CoffeeDecorator
 */
public class Milk extends CoffeeDecorator {

    /**
     * 牛奶配料价格
     */
    private static final double MILK_PRICE = 2.0D;

    /**
     * 构造牛奶装饰器
     *
     * @param drink 被装饰的饮品对象
     */
    public Milk(Drink drink) {
        super(drink);
    }

    /**
     * {@inheritDoc}
     *
     * <p>增强功能：在原价基础上加牛奶价格</p>
     *
     * @return 原价格 + 2.0
     */
    @Override
    public double cost() {
        return super.cost() + MILK_PRICE;
    }

    /**
     * {@inheritDoc}
     *
     * <p>增强功能：在描述后追加牛奶信息</p>
     *
     * @return 原描述 + " + 奶"
     */
    @Override
    public String desc() {
        return super.desc() + " + 奶";
    }
}
