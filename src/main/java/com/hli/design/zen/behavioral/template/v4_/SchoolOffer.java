package com.hli.design.zen.behavioral.template.v4_;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体实现：校招 Offer 流程
 *
 * <p>校招流程包含 HR 面试环节，因此：</p>
 * <ul>
 *     <li>继承父类默认钩子（{@code isNeedHrInterview()} 返回 {@code true}）</li>
 *     <li>重写 {@link #hrInterview()} 提供具体的 HR 面试逻辑</li>
 * </ul>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class SchoolOffer extends AbstractGetOfferModel {

    /** {@inheritDoc} */
    @Override
    protected void read() {
        log.info("校招简历筛选~");
    }

    /** {@inheritDoc} */
    @Override
    protected void writtenExam() {
        log.info("校招笔试~");
    }

    /** {@inheritDoc} */
    @Override
    protected void interview() {
        log.info("校招面试");
    }

    /**
     * 校招需要 HR 面试，重写父类空实现
     */
    @Override
    protected void hrInterview() {
        log.info("校招有hr面！");
    }

    /** {@inheritDoc} */
    @Override
    protected void getOffer() {
        log.info("恭喜您通过面试，校招offer请查收~");
    }
}
