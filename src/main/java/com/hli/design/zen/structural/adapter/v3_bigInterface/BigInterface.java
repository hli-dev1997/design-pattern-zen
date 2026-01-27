package com.hli.design.zen.structural.adapter.v3_bigInterface;

/**
 * 复杂的"胖接口"示例 - 包含大量抽象方法
 *
 * <p>在实际开发中，当接口定义了过多方法时，实现类被迫实现所有方法，
 * 即使只需要其中一两个。此时可以使用<b>缺省适配器模式</b>来解决。</p>
 *
 * @author hli
 * @see BigInterfaceAdapter
 */
public interface BigInterface {

    /**
     * 功能方法 A
     */
    void a();

    /**
     * 功能方法 B
     */
    void b();

    // ... 假设这里有 c() ~ y() 共24个方法 ...

    /**
     * 功能方法 Z
     */
    void z();
}
