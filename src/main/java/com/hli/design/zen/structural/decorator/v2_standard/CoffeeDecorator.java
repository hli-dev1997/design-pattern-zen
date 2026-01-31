package com.hli.design.zen.structural.decorator.v2_standard;

import com.hli.design.zen.structural.decorator.common.Drink;

/**
 * 咖啡装饰器基类 - 抽象装饰器 (Decorator)
 *
 * <p><b>装饰器模式核心！</b></p>
 * <p>必须同时满足两个条件：</p>
 * <ol>
 *     <li><b>实现 {@link Drink} 接口：</b>让外层装饰器可以继续套娃</li>
 *     <li><b>持有 {@link Drink} 引用：</b>用于调用内层对象的方法</li>
 * </ol>
 *
 * <p><b>设计要点：</b></p>
 * <ul>
 *     <li>默认实现直接委托给被装饰对象</li>
 *     <li>具体装饰器通过覆写方法来增强功能</li>
 * </ul>
 *
 * @author hli
 * @since 2026-01-30
 * @see Drink
 * @see Milk
 * @see Sugar
 */
public abstract class CoffeeDecorator implements Drink {

    /**
     * 被装饰的饮品对象（内层对象）
     *
     * <p>使用 protected 修饰，允许子类直接访问</p>
     */
    protected Drink drink;

    /**
     * 构造装饰器
     *
     * @param drink 被装饰的饮品对象，不能为 null
     */
    public CoffeeDecorator(Drink drink) {
        this.drink = drink;
    }

    /**
     * {@inheritDoc}
     *
     * <p>默认实现：委托给被装饰对象</p>
     *
     * @return 被装饰对象的价格
     */
    @Override
    public double cost() {
        return drink.cost();
    }

    /**
     * {@inheritDoc}
     *
     * <p>默认实现：委托给被装饰对象</p>
     *
     * @return 被装饰对象的描述
     */
    @Override
    public String desc() {
        return drink.desc();
    }
}
