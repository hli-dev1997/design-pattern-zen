/**
 * 享元模式（Flyweight Pattern）
 *
 * <p>运用共享技术有效地支持大量细粒度对象的复用，
 * 通过共享已存在的对象来减少内存消耗和创建开销。</p>
 *
 * <ul>
 *     <li>common - 公共组件（享元接口、享元工厂）</li>
 *     <li>v1_simple - 简单享元：单纯的对象池实现</li>
 *     <li>v2_composite - 复合享元：享元与组合模式结合</li>
 * </ul>
 *
 * @author hli
 * @see com.hli.design.zen.structural.flyweight.common
 * @see com.hli.design.zen.structural.flyweight.v1_simple
 * @see com.hli.design.zen.structural.flyweight.v2_composite
 */
package com.hli.design.zen.structural.flyweight;
