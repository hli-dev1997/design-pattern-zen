package com.hli.design.zen.structural.decorator.v2_standard;

import com.hli.design.zen.structural.decorator.common.Drink;

/**
 * 糖装饰器 - 具体装饰器 (ConcreteDecorator)
 *
 * <p>为饮品添加糖配料，增加价格并修改描述。</p>
 *
 * <p><b>功能增强：</b></p>
 * <ul>
 *     <li>价格 +1.0</li>
 *     <li>描述追加 " + 糖"</li>
 * </ul>
 *
 * @author hli
 * @since 2026-01-30
 * @see CoffeeDecorator
 */
public class Sugar extends CoffeeDecorator {

    /**
     * 糖配料价格
     */
    private static final double SUGAR_PRICE = 1.0D;

    /**
     * 构造糖装饰器
     *
     * @param drink 被装饰的饮品对象
     */
    public Sugar(Drink drink) {
        super(drink);
    }

    /**
     * {@inheritDoc}
     *
     * <p>增强功能：在原价基础上加糖价格</p>
     *
     * @return 原价格 + 1.0
     */
    @Override
    public double cost() {
        return super.cost() + SUGAR_PRICE;
    }

    /**
     * {@inheritDoc}
     *
     * <p>增强功能：在描述后追加糖信息</p>
     *
     * @return 原描述 + " + 糖"
     */
    @Override
    public String desc() {
        return super.desc() + " + 糖";
    }
}
