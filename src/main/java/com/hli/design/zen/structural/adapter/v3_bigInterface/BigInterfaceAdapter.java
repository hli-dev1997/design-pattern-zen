package com.hli.design.zen.structural.adapter.v3_bigInterface;

/**
 * 缺省适配器（Default Adapter）- 为 BigInterface 提供空实现骨架
 *
 * <p>本类是适配器模式的变体，也叫<b>接口适配器</b>或<b>缺省适配模式</b>。
 * 它为拥有大量方法的接口提供一个抽象的空实现类，
 * 让具体的业务类只需覆写自己关心的方法，无需实现全部接口方法。</p>
 *
 * <h3>使用场景</h3>
 * <ul>
 *   <li>接口方法过多（"胖接口"）</li>
 *   <li>实现类只需要其中少量方法</li>
 *   <li>避免大量空实现代码污染业务类</li>
 * </ul>
 *
 * @author hli
 * @see BigInterface
 * @see MyServiceImpl
 */
public abstract class BigInterfaceAdapter implements BigInterface {

    /**
     * {@inheritDoc}
     * <p>默认空实现，子类按需覆写</p>
     */
    @Override
    public void a() {
        // 空实现 - 子类选择性覆写
    }

    /**
     * {@inheritDoc}
     * <p>默认空实现，子类按需覆写</p>
     */
    @Override
    public void b() {
        // 空实现 - 子类选择性覆写
    }

    /**
     * {@inheritDoc}
     * <p>默认空实现，子类按需覆写</p>
     */
    @Override
    public void z() {
        // 空实现 - 子类选择性覆写
    }
}
