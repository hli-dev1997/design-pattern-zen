package com.hli.design.zen.behavioral.template.v4_;

/**
 * V4 包心实现：获取 Offer 流程的抽象模板类
 *
 * <p>综合运用模板方法模式的三大核心要素：</p>
 * <ul>
 *     <li>模板方法（{@link #run()}）：定义算法骨架，加 {@code final} 防止子类破坏流程</li>
 *     <li>抽象方法（{@link #read()}, {@link #writtenExam()} 等）：强制子类实现差异化步骤</li>
 *     <li>钩子方法（{@link #isNeedHrInterview()}）：子类可选择性地反向控制父类流程</li>
 * </ul>
 *
 * <p>场景：校招和社招的面试流程大部分相同，但校招多一轮 HR 面试。
 * 通过钩子方法让社招子类跳过 HR 面，而不是让社招被迫实现一个空方法。</p>
 *
 * @author hli
 * @since 1.0.0
 */
abstract class AbstractGetOfferModel {

    /**
     * 模板方法：定义获取 Offer 的完整流程
     *
     * <p>加 {@code final} 防止子类覆盖或修改流程顺序</p>
     */
    public final void run() {
        // 第一步：阅读简历
        read();
        // 第二步：笔试
        writtenExam();
        // 第三步：面试
        interview();
        // 第四步：钩子方法判断是否需要 HR 面
        if (isNeedHrInterview()) {
            hrInterview();
        }
        // 第五步：发放 Offer
        getOffer();
    }

    /**
     * HR 阅读简历
     */
    protected abstract void read();

    /**
     * 笔试环节
     */
    protected abstract void writtenExam();

    /**
     * 技术面试环节
     */
    protected abstract void interview();

    /**
     * HR 面试环节（校招有，社招无）
     *
     * <p>提供默认空实现，这样社招子类不需要被迫重写此方法。
     * 配合钩子方法 {@link #isNeedHrInterview()} 使用，
     * 当钩子返回 {@code false} 时此方法不会被调用。</p>
     */
    protected void hrInterview() {
        // 默认为空实现，子类按需重写
    }

    /**
     * 钩子方法 (Hook Method)：是否需要 HR 面试
     *
     * <p>默认返回 {@code true}（即默认需要 HR 面），
     * 社招子类可重写此方法返回 {@code false} 以跳过 HR 面。</p>
     *
     * @return 是否需要 HR 面试
     */
    protected boolean isNeedHrInterview() {
        return true;
    }

    /**
     * 发放 Offer
     */
    protected abstract void getOffer();
}
