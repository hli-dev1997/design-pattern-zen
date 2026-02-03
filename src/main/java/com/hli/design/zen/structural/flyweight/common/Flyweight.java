package com.hli.design.zen.structural.flyweight.common;

/**
 * 抽象享元 (Flyweight): 享元模式中的抽象角色
 *
 * <p>定义享元对象的接口，同时定义了接收外部状态的方法。
 * 通过这个接口，享元可以接受并作用于外部状态</p>
 *
 * @author hli
 * @since 1.0.0
 */
public interface Flyweight {

    /**
     * 执行业务操作
     *
     * <p>接收外部状态作为参数，与内部状态结合完成业务逻辑</p>
     *
     * @param extrinsicState 外部状态（由客户端传入，不可共享）
     */
    void operation(String extrinsicState);
}
