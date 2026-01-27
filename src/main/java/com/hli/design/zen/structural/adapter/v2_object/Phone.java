package com.hli.design.zen.structural.adapter.v2_object;

import com.hli.design.zen.structural.adapter.common.AC220V;

/**
 * @author hli
 * @program: design-pattern-zen
 * @Date 2026-01-27 08:07:30
 * @description: 客户端
 */
public class Phone {
    public static void main(String[] args) {
        DC5V dc5V = new PowerAdapter(new AC220V());
        charge(dc5V);
    }
    //手机只依赖标准的5V接口
    public static void charge(DC5V dc5V){
        if (dc5V.output5V() == 5){
            System.out.println("✅ 电压正常，开始充电...");        }
    }
}
