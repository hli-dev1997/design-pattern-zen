package com.hli.design.zen.structural.flyweight.v2_composite;

import com.hli.design.zen.structural.flyweight.common.SignInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * V2 享元模式：共享内部状态，节省内存
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V2 享元模式 ===");

        // 通过工厂获取享元对象（不是直接 new）
        SignInfo s1 = SignInfoFactory.getSignInfo("北京一中", "申论");
        s1.display("考生A");  // 外部状态通过参数传入

        // 再次获取相同的考场+科目，会复用已有对象
        SignInfo s2 = SignInfoFactory.getSignInfo("北京一中", "申论");
        s2.display("考生B");

        // 验证：s1 和 s2 是同一个对象
        log.info("s1 == s2? {} (复用同一对象！)\n", s1 == s2);
    }
}

/**
 * 享元工厂 (FlyweightFactory)
 *
 * <p>职责：管理享元对象的创建和复用</p>
 * <p>核心：使用 Map 缓存已创建的享元对象</p>
 */
@Slf4j
class SignInfoFactory {

    /**
     * 享元池：缓存已创建的享元对象
     * Key = "地点-科目"，Value = 享元对象
     */
    private static final Map<String, SignInfo> pool = new HashMap<>();

    /**
     * 获取享元对象
     *
     * <p>如果池中存在则直接返回，否则创建新对象并放入池中</p>
     *
     * @param location 考试地点
     * @param subject  考试科目
     * @return 享元对象
     */
    public static SignInfo getSignInfo(String location, String subject) {
        // 复合 Key：确保相同地点+科目返回同一对象
        String key = location + "-" + subject;

        // computeIfAbsent：如果不存在才创建
        return pool.computeIfAbsent(key, k -> {
            log.info(">>> 创建新考场: {}", k);
            return new SignInfo(location, subject);
        });
    }
}
