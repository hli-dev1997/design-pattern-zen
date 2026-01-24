package com.hli.design.zen.creational.prototype.v2_clone;

import com.hli.design.zen.creational.prototype.Mail;
import lombok.extern.slf4j.Slf4j;


/**
 * @author hli
 * @program: design-pattern-zen
 * @Date 2026-01-24 17:33:44
 * @description: V2模式重构：原型模式(Standard Clone)
 */
@Slf4j
public class CloneFactory {
    public static void main(String[] args) {
        log.info("===== 开始发送邮件 (原型模式版) =====");
        long start = System.currentTimeMillis();

        // 1. 先产生一个“种子”邮件 (原型)
        Mail protoMail = new Mail();
        protoMail.setSubject("初始化通用主题");
        protoMail.setContext("初始化通用内容...");

        // 2. 循环克隆
        sendMails(protoMail);

        long end = System.currentTimeMillis();
        log.info("发送完成，总耗时: {} ms", (end - start));
        // 在循环结束后添加这一行
        //原型本体的地址: []
        log.info("原型本体的地址: {}", protoMail.getAddress());
    }

    private static void sendMails(Mail mail) {
        int i = 0;
        int max = 1000;
        while (i < max) {
            //重点：不再是new，而是clone
            Mail cloneMail = mail.clone();
            //个性化设置
            cloneMail.setReceiver("user" + i);
            cloneMail.setAppellation("先生/女士");
            cloneMail.getAddress().add("湖南省" + i);
            send(cloneMail);
            i++;
        }
    }

    /**
     * 发送完成，总耗时: 37 ms
     *
     * @param mail 邮件对象
     */
    private static void send(Mail mail) {
        log.info("send_user={}, sub={},list={}", mail.getReceiver(), mail.getSubject(), mail.getAddress());
    }
}
