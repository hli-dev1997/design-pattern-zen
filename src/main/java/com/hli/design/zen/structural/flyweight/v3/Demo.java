package com.hli.design.zen.structural.flyweight.v3;

import com.hli.design.zen.structural.flyweight.common.SignInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * V3 线程安全版：使用 ConcurrentHashMap
 *
 * <p>相比 V2 的改进：支持多线程并发访问</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V3 线程安全版 ===");

        SignInfo s1 = SignInfoFactory.getSignInfo("北京一中", "申论");
        s1.display("考生A");

        SignInfo s2 = SignInfoFactory.getSignInfo("北京一中", "申论");
        s2.display("考生B");

        log.info("s1 == s2? {}", s1 == s2);
    }
}

/**
 * 线程安全的享元工厂
 *
 * <p>改进点：
 * <ul>
 *     <li>使用 {@link ConcurrentHashMap} 替代 HashMap</li>
 *     <li>computeIfAbsent 保证并发下只创建一次</li>
 * </ul>
 * </p>
 */
@Slf4j
class SignInfoFactory {
    /**
     * 线程安全的享元池
     *
     * <p>ConcurrentHashMap 的 computeIfAbsent 在并发场景下
     * 保证同一个 key 只会创建一次对象</p>
     */
    private static final Map<String, SignInfo> pool = new ConcurrentHashMap<>();

    public static SignInfo getSignInfo(String location, String subject) {
        String key = location + "-" + subject;
        List<String> list = new ArrayList<>();
        // ConcurrentHashMap.computeIfAbsent 是线程安全的
        return pool.computeIfAbsent(key, k -> {
            log.info(">>> 创建新考场(线程安全): {}", k);
            return new SignInfo(location, subject);
        });
    }
}
