package com.hli.design.zen.structural.bridge.common;

/**
 * 实现化角色 (Implementor): 桥接模式中的实现接口
 *
 * <p>定义实现类的接口，该接口不必与抽象类的接口一致。
 * 实现化角色仅提供基本操作，抽象化角色定义的更高层次行为基于这些操作实现</p>
 *
 * @author hli
 * @since 1.0.0
 */
public interface Implementor {

    /**
     * 执行底层实现操作
     *
     * <p>具体实现由子类提供，抽象层调用此方法完成高层操作</p>
     */
    void operationImpl();
}
