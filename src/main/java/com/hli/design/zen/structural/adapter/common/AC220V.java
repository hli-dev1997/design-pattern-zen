package com.hli.design.zen.structural.adapter.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 被适配者 (Adaptee): 家用 220V 交流电源
 *
 * <p>这是一个"老接口"或"不兼容的接口"，提供 220V 的交流电输出。
 * 手机等设备无法直接使用，需要通过适配器转换为 5V 直流电</p>
 *
 * <p><b>注意：</b>作为被适配者，此类代码不应被修改（老代码）</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class AC220V {

    /**
     * 输出 220V 交流电
     *
     * @return 220V 电压值
     */
    public int output220V() {
        int src = 220;
        log.info("输出交流电压: {}V", src);
        return src;
    }
}
