package com.hli.design.zen.structural.adapter.v2_object;

import com.hli.design.zen.structural.adapter.common.AC220V;
import lombok.extern.slf4j.Slf4j;

/**
 * 适配器 (Adapter): 电源适配器，将 220V 交流电转换为 5V 直流电
 *
 * <p>采用对象适配器模式（组合方式），通过持有 AC220V 对象实现电压转换。
 * 遵循单一职责原则，不修改被适配者（老接口）</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class PowerAdapter implements DC5V {

    /**
     * 电压转换比例 (220V / 44 ≈ 5V)
     */
    private static final int VOLTAGE_RATIO = 44;

    /**
     * 被适配对象：220V 交流电源
     */
    private final AC220V ac220V;

    /**
     * 构造适配器，传入被适配对象
     *
     * @param ac220V 220V 交流电源（被适配者）
     */
    public PowerAdapter(AC220V ac220V) {
        this.ac220V = ac220V;
    }

    /**
     * 输出 5V 直流电
     *
     * <p>核心适配逻辑：将 220V 交流电降压为 5V 直流电</p>
     *
     * @return 5V 电压值
     */
    @Override
    public int output5V() {
        int output = ac220V.output220V();
        // 变压逻辑：220V → 5V
        int adapterOutput = output / VOLTAGE_RATIO;
        log.info("🔌 适配器工作：将 {}V 降压为 {}V", output, adapterOutput);
        return adapterOutput;
    }
}
