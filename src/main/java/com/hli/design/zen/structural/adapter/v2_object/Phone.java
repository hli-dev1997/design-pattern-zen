package com.hli.design.zen.structural.adapter.v2_object;

import com.hli.design.zen.structural.adapter.common.AC220V;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端 (Client): 手机
 *
 * <p>客户端只依赖目标接口 {@link DC5V}，不关心电源的具体实现。
 * 通过适配器 {@link PowerAdapter} 将 220V 电源转换为可用的 5V 电源</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Phone {

    /**
     * 演示适配器模式的使用
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 通过适配器将 220V 电源适配为 5V 接口
        DC5V dc5V = new PowerAdapter(new AC220V());
        charge(dc5V);
    }

    /**
     * 手机充电方法
     *
     * <p>手机只依赖标准的 5V 接口，不关心电源来源</p>
     *
     * @param dc5V 5V 直流电源接口
     */
    public static void charge(DC5V dc5V) {
        if (dc5V.output5V() == 5) {
            log.info("✅ 电压正常，开始充电...");
        }
    }
}
