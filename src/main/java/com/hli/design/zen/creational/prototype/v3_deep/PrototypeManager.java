package com.hli.design.zen.creational.prototype.v3_deep;

import com.hli.design.zen.creational.prototype.Mail;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 🏭 原型管理器 (Registry)
public class PrototypeManager {
    // ✅ 升级：使用 ConcurrentHashMap 保证线程安全
    private static Map<String, Mail> prototypeCache = new ConcurrentHashMap<>();

    // 私有化构造
    private PrototypeManager() {}

    // 🌱 初始化：模拟系统启动时加载默认配置
    static {
        Mail mailTemplate = new Mail();
        mailTemplate.setSubject("【系统通知】");
        mailTemplate.setContext("默认内容...");
        // 注册到缓存池
        prototypeCache.put("system_mail", mailTemplate);
    }

    // 🔑 注册新的原型
    public static void setPrototype(String key, Mail mail) {
        prototypeCache.put(key, mail);
    }

    // 🚀 获取副本 (自动深拷贝)
    public static Mail getPrototype(String key) {
        // 关键点：这里调用通用工具，直接返回“深拷贝”后的新对象
        // 即使多个线程同时取，拿到的也是各自独立的副本
        //只有拿到了 Serializable（序列化）通行证的对象，才允许进入这个深拷贝方法。
        return PrototypeUtil.deepClone(prototypeCache.get(key));
    }
}