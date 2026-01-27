/**
 * 类适配器（Class Adapter）
 *
 * <p>通过继承被适配者类来实现适配。</p>
 *
 * <ul>
 *     <li>优点：可以重写被适配者的方法</li>
 *     <li>缺点：Java 单继承限制，只能适配一个类</li>
 * </ul>
 *
 * @author hli
 */
package com.hli.design.zen.structural.adapter.v1_class;

import com.hli.design.zen.structural.adapter.common.AC220V;

/**
 * // ❌ 错误示范：客户端直接依赖了具体的“老接口”，或者强行修改老类
 */
public class Phone {
    public static void main(String[] args) {
        charge();
    }

    private static void charge() {
        AC220V ac220V = new AC220V();
        int output = ac220V.output220V();
        // 😱 客户端代码里掺杂了复杂的转换逻辑
        // 违反了单一职责原则，手机不应该关心怎么把 220 变 5V
        int finalVol = output / 44;
        System.out.println("手机正在以 " + finalVol + "V 充电");
    }
}