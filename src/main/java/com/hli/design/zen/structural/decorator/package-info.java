/**
 * 装饰器模式（Decorator Pattern）
 *
 * <p>动态地给一个对象添加一些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活。</p>
 *
 * <ul>
 *     <li>v1_bad - 类爆炸 (Class Explosion)：展示通过继承扩展功能的弊端</li>
 *     <li>v2_standard - 模式重构 (标准写法)：实现经典的装饰器模式（俄罗斯套娃）</li>
 *     <li>v3_architect - 架构师版本 (链式构建 / 配置化)：展示更高级、更易用的实现方式</li>
 * </ul>
 *
 * @author hli
 * @see com.hli.design.zen.structural.decorator.v1_bad
 * @see com.hli.design.zen.structural.decorator.v2_standard
 * @see com.hli.design.zen.structural.decorator.v3_architect
 */
package com.hli.design.zen.structural.decorator;
