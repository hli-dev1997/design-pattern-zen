package com.hli.design.zen.creational.prototype;

import lombok.Data;

/**
 * 邮件类（烂代码版 - 每次都 new）
 * <p>
 * 设计目的：
 * 1. 模拟一个初始化开销较大的对象。
 * 2. 演示在高并发或循环场景下，频繁 new 对象的性能问题。
 * <p>
 * 为什么需要该类：
 * 作为反面教材。在 DirectNew 类中，我们将循环 new 这个对象，观察耗时。
 * <p>
 * 核心实现思路：
 * - 构造函数中模拟耗时操作（Thread.sleep）。
 * - 包含收件人、标题、内容等属性。
 */
@Data
public class Mail {
    // 收件人
    private String receiver;
    // 邮件标题
    private String subject;
    // 邮件内容
    private String content;
}
