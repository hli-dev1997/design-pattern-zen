package com.hli.design.zen.structural.decorator.v1_bad;

/**
 * ❌ 反模式示例：通过继承实现功能组合
 *
 * <p><b>问题：类爆炸 (Class Explosion)</b></p>
 * <p>当需要组合多种配料时，子类数量呈指数级增长：</p>
 * <ul>
 *     <li>2 种配料 → 4 个子类</li>
 *     <li>3 种配料 → 8 个子类</li>
 *     <li>n 种配料 → 2^n 个子类</li>
 * </ul>
 *
 * <p><b>正确做法：</b>使用装饰器模式，通过组合而非继承来扩展功能。</p>
 *
 * @author hli
 * @see com.hli.design.zen.structural.decorator.v2_standard
 */
class Coffee {
}

/**
 * 加奶咖啡 - 继承方式实现
 */
class CoffeeWithMilk extends Coffee {
}

/**
 * 加糖咖啡 - 继承方式实现
 */
class CoffeeWithSugar extends Coffee {
}

/**
 * 加奶加糖咖啡 - 😱 噩梦的开始，组合越多类越多
 */
class CoffeeWithMilkAndSugar extends Coffee {
}