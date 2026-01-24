package com.hli.design.zen.creational.prototype.v3_deep;

import java.io.*;

// 🧰 通用深拷贝工具类 (解决所有类的深拷贝问题)
public class PrototypeUtil {

    // 泛型方法：输入 T，返回 T
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepClone(T object) {
        try {
            // 1. 写入流 (序列化) -> 把对象变成二进制流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);

            // 2. 读出流 (反序列化) -> 从流里“复活”出一个新对象
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            
            // 🌟 奇迹时刻：此时返回的是一个内存中全新的对象
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}