package com.hli.design.zen.behavioral.template.v4_;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体实现：社招 Offer 流程
 *
 * <p>社招流程不包含 HR 面试环节，因此：</p>
 * <ul>
 *     <li>重写钩子方法 {@link #isNeedHrInterview()} 返回 {@code false}，跳过 HR 面</li>
 *     <li>无需重写 {@link #hrInterview()}，父类空实现即可</li>
 * </ul>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class SocialOffer extends AbstractGetOfferModel {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void read() {
        log.info("社招简历筛选~");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writtenExam() {
        log.info("社招笔试~");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void interview() {
        log.info("社招面试");
    }

    /**
     * 重写钩子方法，返回 {@code false} 以跳过 HR 面试
     *
     * <p>父类模板方法中 {@code if (isNeedHrInterview())} 将因此不再执行 {@code hrInterview()}</p>
     *
     * @return 始终返回 {@code false}，社招不需要 HR 面
     */
    @Override
    protected boolean isNeedHrInterview() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void getOffer() {
        log.info("恭喜您通过面试，社招offer请查收~");
    }
}
