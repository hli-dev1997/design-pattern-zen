package com.hli.design.zen.structural.adapter.v3_bigInterface;

/**
 * 具体业务实现类 - 只覆写需要的方法
 *
 * <p>通过继承 {@link BigInterfaceAdapter}，本类无需实现 {@link BigInterface} 的所有方法，
 * 只需专注于业务真正关心的 {@link #a()} 方法。</p>
 *
 * <p>这正是<b>缺省适配器模式</b>的核心价值：让业务代码保持简洁，
 * 避免被大量无关的空实现代码所污染。</p>
 *
 * @author hli
 * @see BigInterfaceAdapter
 */
public class MyServiceImpl extends BigInterfaceAdapter {

    /**
     * 实现业务核心逻辑
     * <p>只覆写我们真正关心的方法，其他方法由父类适配器提供空实现</p>
     */
    @Override
    public void a() {
        System.out.println("我只关心方法a，其它的我不管！更不想实现");
    }
}
