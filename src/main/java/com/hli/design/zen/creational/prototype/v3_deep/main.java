package com.hli.design.zen.creational.prototype.v3_deep;

import com.hli.design.zen.creational.prototype.Mail;

/**
 * @author hli
 * @program: design-pattern-zen
 * @Date 2026-01-24 20:04:44
 * @description:
 */
public class main {
    public static void main(String[] args) {
        // 1. 即使不 new，也能拿到对象
        Mail mail1 = PrototypeManager.getPrototype("system_mail");
        Mail mail2 = PrototypeManager.getPrototype("system_mail");

        // 2. 验证深拷贝
        mail1.getAddress().add("上海"); // 修改 mail1 的地址

        System.out.println("mail1 地址: " + mail1.getAddress()); // [上海]
        System.out.println("mail2 地址: " + mail2.getAddress()); // [] (互不干扰)
    }
}
