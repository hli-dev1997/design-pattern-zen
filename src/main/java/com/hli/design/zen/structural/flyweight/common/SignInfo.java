package com.hli.design.zen.structural.flyweight.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 享元类 (Flyweight)
 *
 * <p>核心设计：
 * <ul>
 *     <li><b>内部状态</b>：location、subject - 可共享，用 final 修饰防止修改</li>
 *     <li><b>外部状态</b>：externalId - 不可共享，通过方法参数传入</li>
 * </ul>
 * </p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class SignInfo {

    /**
     * 内部状态：考试地点（共享）
     * 必须用 final，防止被修改导致数据错乱
     */
    protected final String location;

    /**
     * 内部状态：考试科目（共享）
     */
    protected final String subject;

    public SignInfo(String location, String subject) {
        this.location = location;
        this.subject = subject;
    }

    /**
     * 业务方法
     *
     * <p><b>关键</b>：外部状态（考生ID）通过参数传入，不存储在对象中！</p>
     *
     * @param externalId 外部状态 - 考生ID
     */
    public void display(String externalId) {
        log.info("考生[{}] -> 考场: {} | 科目: {}", externalId, location, subject);
    }
}
