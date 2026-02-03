/**
 * 桥接模式（Bridge Pattern）
 *
 * <p>将抽象部分与实现部分分离，使它们可以独立变化。
 * 通过组合（而非继承）建立两个独立变化维度之间的关系。</p>
 *
 * <ul>
 *     <li>common - 公共组件（实现化角色接口）</li>
 *     <li>v1_basic - 基础桥接：抽象与实现分离的基本实现</li>
 *     <li>v2_multi - 多维桥接：多个维度独立变化的扩展示例</li>
 * </ul>
 *
 * @author hli
 * @see com.hli.design.zen.structural.bridge.common
 * @see com.hli.design.zen.structural.bridge.v1_basic
 * @see com.hli.design.zen.structural.bridge.v2_multi
 */
package com.hli.design.zen.structural.bridge;
