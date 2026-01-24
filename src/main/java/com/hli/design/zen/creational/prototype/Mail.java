package com.hli.design.zen.creational.prototype;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 邮件
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

/**
 * 邮件类 (实现了 Cloneable，为 V2 做准备)
 */
@Data
@Slf4j
//只有拿到了 Serializable（序列化）通行证的对象，才允许进入这个深拷贝方法。
public class Mail implements Cloneable, Serializable {
    // 收件人
    private String receiver;
    // 邮件名称
    private String subject;
    // 邮件称谓 (这个字段用来演示深浅拷贝的坑)
    private String appellation;
    // 邮件内容
    private String context;
    // 邮件尾部
    private String tail;

    /**
     * 邮件地址
     * 例如：湖南省、长沙市、岳麓区、长沙理工大学
     */
    private List<String> address = new ArrayList<>();


    /**
     * 构造函数
     * 🌟 重点：模拟繁琐的初始化过程
     */
    public Mail() {
        log.info("【构造函数执行】正在加载模板、连接数据库... (耗时操作)");
        try {
            // 模拟耗时操作，比如阻塞 10毫秒 (太长你会等疯的，因为循环600万次)
            // 在真实高并发下，这10ms就是致命的
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写 clone 方法 (为 V2 准备)
     */
    @Override
    public Mail clone() {
        Mail mail = null;
        try {
            //核心：调用Java原生的内存拷贝
            mail = (Mail) super.clone();
            // ✅ 推荐写法：新建一个 ArrayList，把旧数据倒进去
//            mail.setAddress(new ArrayList<>(this.address));
            // ❌ 写法繁琐：先转成 ArrayList 调 clone，由于 clone 返回 Object，还得再转回 List
//            if (this.address instanceof ArrayList) {
//                mail.setAddress((List<String>) ((ArrayList<String>) this.address).clone());
//            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return mail;
    }
}