/**
 * ❌ 烂代码示范
 *
 * 场景：
 * 假设我们需要发送 100 万封账单邮件。
 * 如果每次都 new 一个 Mail 对象，不仅耗时，而且如果 Mail 构造函数里有耗时操作（如查库），系统会直接崩掉。
 * 或者，虽然用了 clone，但是是“浅拷贝”，导致所有邮件的收件人变成了同一个人（灾难）。
 */
package com.hli.design.zen.creational.prototype.v1_bad;

import com.hli.design.zen.creational.prototype.Mail;
import lombok.extern.slf4j.Slf4j;

/**
 * V1 烂代码：循环硬造 (Direct New)
 *
 * 设计目的：
 * 1. 演示不使用原型模式时的性能灾难。
 * 2. 验证每次 new 对象都会触发耗时的构造函数。
 *
 * 痛点：
 * 每次 new 都要执行构造函数。
 * 如果构造函数里要连数据库、解析模板，这代码能跑慢到让你怀疑人生。
 */
@Slf4j
public class DirectNew{
    public static void main(String[] args) {
        log.info("===== 开始发送邮件 (烂代码版) =====");
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 执行任务
        sendMails();
        // 记录结束时间
        long end = System.currentTimeMillis();
        // 打印耗时日志
        log.info("发送完成，总耗时: {} ms|Send_completed,duration={}", (end - start), (end - start));
        log.info("===== 结束 =====");
    }
    private static   void sendMails(){
        int i = 0;
        while (i < 6000000){
            //每次都从头开始初始化，耗时耗力
            Mail mail = new Mail();
            mail.setSubject("主题" + i);//假设这里很耗时
            mail.setReceiver("user" + i);
            send(mail);
            i++;
        }
    }

    private static void send(Mail mail) {
        log.info("send_user={},sub={}",mail.getReceiver(),mail.getSubject());
    }
}