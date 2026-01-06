package com.hli.design.zen.creational.singleton;

import com.hli.design.zen.common.config.ThreadPoolConfig;
import com.hli.design.zen.creational.singleton.v1_bad.EmperorBad;
import com.hli.design.zen.creational.singleton.v2_dcl.EmperorDcl;
import com.hli.design.zen.creational.singleton.v3_static_inner.EmperorStatic;
import com.hli.design.zen.creational.singleton.v4_enum.EmperorEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单例模式全方位基准测试
 *
 * 测试目的：
 * 1. 验证不同单例实现的线程安全性。
 * 2. 对比不同实现的性能差异（吞吐量）。
 *
 * 测试环境：
 * - 线程池：ThreadPoolConfig (使用 quantTaskExecutor)
 * - 并发数：1000
 * - 循环次数：10000
 */
@Slf4j
@SpringBootTest
@Import(ThreadPoolConfig.class)
public class SingletonBenchmarkTest {

    @Autowired
    @Qualifier("quantTaskExecutor")
    private ThreadPoolTaskExecutor executorService;

    // 并发线程数
    private static final int THREAD_COUNT = 100;
    // 每个线程获取单例的次数
    private static final int LOOP_COUNT = 10000;

    @Test
    @DisplayName("V1 烂代码：安全性与性能测试")
    public void testEmperorBad() throws InterruptedException {
        log.info("========== 开始测试 V1: EmperorBad (烂代码) ==========");
        runTest("EmperorBad", () -> EmperorBad.getInstance());
    }

    @Test
    @DisplayName("V2 DCL：安全性与性能测试")
    public void testEmperorDcl() throws InterruptedException {
        log.info("========== 开始测试 V2: EmperorDcl (双重检查锁) ==========");
        runTest("EmperorDcl", () -> EmperorDcl.getInstance());
    }

    @Test
    @DisplayName("V3 静态内部类：安全性与性能测试")
    public void testEmperorStatic() throws InterruptedException {
        log.info("========== 开始测试 V3: EmperorStatic (静态内部类) ==========");
        runTest("EmperorStatic", () -> EmperorStatic.getInstance());
    }

    @Test
    @DisplayName("V4 枚举单例：安全性与性能测试")
    public void testEmperorEnum() throws InterruptedException {
        log.info("========== 开始测试 V4: EmperorEnum (枚举单例) ==========");
        // 枚举单例可以直接访问 INSTANCE
        runTest("EmperorEnum", () -> EmperorEnum.INSTANCE);
    }

    /**
     * 通用测试逻辑
     * @param name 测试对象名称
     * @param supplier 获取实例的 Lambda
     */
    private void runTest(String name, java.util.function.Supplier<Object> supplier) throws InterruptedException {
        Set<Integer> instanceCodes = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        StopWatch stopWatch = new StopWatch();
        AtomicInteger totalCalls = new AtomicInteger(0);

        stopWatch.start();

        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    for (int j = 0; j < LOOP_COUNT; j++) {
                        Object instance = supplier.get();
                        // 为了避免 HashSet 过于庞大影响性能，我们只采样记录 hashCode
                        // 或者在出现不同实例时才记录
                        if (j == 0) { 
                            instanceCodes.add(System.identityHashCode(instance));
                        }
                        totalCalls.incrementAndGet();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        stopWatch.stop();

        // 结果分析
        long timeMillis = stopWatch.getTotalTimeMillis();
        log.info("[{}] 测试完成|Test_finished,name={}", name, name);
        log.info("耗时|Time_elapsed,millis={}", timeMillis);
        log.info("总调用次数|Total_calls,count={}", totalCalls.get());
        
        // 安全性验证
        if (instanceCodes.size() > 1) {
            log.error("❌ 线程安全失败！生成了 {} 个实例|Thread_safety_failed,count={}", instanceCodes.size(), instanceCodes.size());
            instanceCodes.forEach(code -> log.error("实例ID|Instance_ID,id={}", code));
        } else {
            log.info("✅ 线程安全通过。实例唯一。|Thread_safety_passed");
        }
        
        log.info("==================================================");
    }
}
