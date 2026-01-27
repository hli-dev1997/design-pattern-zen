package com.hli.design.zen.structural.adapter.v2_object;

import com.hli.design.zen.structural.adapter.common.AC220V;

/**
 * @author hli
 * @program: design-pattern-zen
 * @Date 2026-01-27 08:01:38
 * @description: 适配器模式(遵循单一职责, 不应修改老接口)
 */
public class PowerAdapter implements DC5V{
    private AC220V ac220V;

    /**
     * 构造器传入旧对象
     * @param ac220V 旧对象
     */
    public PowerAdapter(AC220V ac220V){
        this.ac220V = ac220V;
    }

    @Override
    public int output5V() {
        int output = ac220V.output220V();
        //变压逻辑
        int adapterOutput = output / 44;
        System.out.println("🔌 适配器工作：将 " + output + "V 降压为 " + adapterOutput + "V");
        return adapterOutput;
    }
}
