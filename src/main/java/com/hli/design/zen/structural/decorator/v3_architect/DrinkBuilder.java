package com.hli.design.zen.structural.decorator.v3_architect;

import com.hli.design.zen.structural.decorator.common.Drink;

import java.util.function.Function;

/**
 * 饮品链式构建器 - 架构师版本
 *
 * <p>提供比传统"俄罗斯套娃"更优雅的 API，使用链式调用动态组装装饰器。</p>
 *
 * <p><b>使用示例：</b></p>
 * <pre>{@code
 * Drink myDrink = new DrinkBuilder(new BlackCoffee())
 *     .with(Milk::new)
 *     .with(Sugar::new)
 *     .build();
 * }</pre>
 *
 * <p><b>设计思想：</b></p>
 * <ul>
 *     <li>利用 Java 8 方法引用简化装饰器构造</li>
 *     <li>滚雪球模式：每次 {@link #with(Function)} 调用都将当前饮品包装一层</li>
 *     <li>返回 this 实现链式调用</li>
 * </ul>
 *
 * @author hli
 * @see Drink
 * @since 2026-01-30
 */
public class DrinkBuilder {

    /**
     * 当前正在构建的饮品对象（像滚雪球一样，越滚越大）
     */
    private Drink drink;

    /**
     * 构造饮品构建器
     *
     * <p>传入基底饮品（如 BlackCoffee），作为装饰器链的起点</p>
     *
     * @param drink 基底饮品对象，不能为 null
     */
    public DrinkBuilder(Drink drink) {
        this.drink = drink;
    }

    /**
     * 添加装饰器
     *
     * <p><b>核心魔法：动态混合</b></p>
     * <p>参数 {@code Function<Drink, Drink>} 的含义：</p>
     * <ul>
     *     <li>输入：一个 {@link Drink}（当前饮品）</li>
     *     <li>输出：一个包装后的 {@link Drink}（装饰后的饮品）</li>
     * </ul>
     * <p>恰好，装饰器的构造函数签名 {@code Milk::new} 完美匹配此函数式接口！</p>
     *
     * @param decoratorConstructor 装饰器构造函数引用，如 {@code Milk::new}
     * @return 当前构建器实例，用于链式调用
     */
    public DrinkBuilder with(Function<Drink, Drink> decoratorConstructor) {
        // apply 相当于执行了 new Milk(this.drink)
        // 然后把原来的 drink 替换成新的包装过的 drink
        this.drink = decoratorConstructor.apply(this.drink);
        return this;
    }

    /**
     * 完成构建，返回最终饮品
     *
     * @return 经过所有装饰器包装后的饮品对象
     */
    public Drink build() {
        return this.drink;
    }
}