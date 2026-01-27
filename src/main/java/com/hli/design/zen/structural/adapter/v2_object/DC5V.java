package com.hli.design.zen.structural.adapter.v2_object;

/**
 * 目标接口 (Target): 5V 直流电输出标准
 *
 * <p>客户端（如手机）只依赖此接口进行充电，不关心电源的具体实现</p>
 *
 * @author hli
 * @since 1.0.0
 */
interface DC5V {

    /**
     * 输出 5V 直流电
     *
     * @return 5V 电压值
     */
    int output5V();
}