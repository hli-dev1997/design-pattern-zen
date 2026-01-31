package com.hli.design.zen.structural.decorator.common;

/**
 * 饮品接口 - 抽象组件 (Component)
 *
 * <p>装饰器模式中的核心接口，定义了被装饰对象和装饰器共同遵循的契约。
 * 所有具体饮品（如黑咖啡）和装饰器（如加奶、加糖）都必须实现此接口。</p>
 *
 * <p><b>设计要点：</b></p>
 * <ul>
 *     <li>定义业务核心方法</li>
 *     <li>装饰者和被装饰者必须实现同一接口，保证装饰后的对象仍可被当作原始接口使用</li>
 * </ul>
 *
 * @author hli
 * @see com.hli.design.zen.structural.decorator.v2_standard.BlackCoffee
 * @see com.hli.design.zen.structural.decorator.v2_standard.CoffeeDecorator
 */
public interface Drink {

    /**
     * 计算饮品价格
     *
     * @return 饮品的当前总价格（基础价格 + 配料价格）
     */
    double cost();

    /**
     * 获取饮品描述
     *
     * @return 饮品的描述信息（包含基础饮品和所有配料）
     */
    String desc();
}