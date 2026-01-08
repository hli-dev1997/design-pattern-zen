package com.hli.design.zen.exam.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【单例模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共4道题，每题25分，满分100分
 * 2. 需要在标记 TODO 的地方补充代码
 * 3. 所有测试通过即为满分
 *
 * 验收标准：
 * - 题目1：实现线程不安全的懒汉式单例（反例）
 * - 题目2：实现DCL双重检查锁单例
 * - 题目3：实现静态内部类单例
 * - 题目4：实现枚举单例
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("单例模式考卷")
public class SingletonExam {

    // ==================== 题目1：懒汉式单例（反例）====================
    
    /**
     * 【题目1】实现一个线程不安全的懒汉式单例
     * 
     * 要求：
     * 1. 私有化构造函数
     * 2. 提供静态 getInstance() 方法
     * 3. 故意不加锁，用于演示线程安全问题
     */
    public static class LazySingletonBad {
        // TODO: 补充静态实例变量
        
        // TODO: 私有化构造函数
        
        // TODO: 实现 getInstance() 方法（不加锁）
        public static LazySingletonBad getInstance() {
            // TODO: 补充代码
            return null;
        }
    }

    @Test
    @DisplayName("题目1：验证懒汉式单例结构正确")
    void testLazySingletonBadStructure() {
        // 验证能获取实例
        LazySingletonBad instance = LazySingletonBad.getInstance();
        assertNotNull(instance, "getInstance() 不应返回 null");
        
        // 验证两次调用返回相同实例（单线程下）
        LazySingletonBad instance2 = LazySingletonBad.getInstance();
        assertSame(instance, instance2, "单线程下应返回相同实例");
    }

    // ==================== 题目2：DCL双重检查锁 ====================
    
    /**
     * 【题目2】实现DCL双重检查锁单例
     * 
     * 要求：
     * 1. 使用 volatile 修饰实例变量
     * 2. 第一层检查：避免不必要的同步
     * 3. synchronized 锁类对象
     * 4. 第二层检查：防止重复创建
     */
    public static class DclSingleton {
        // TODO: 使用 volatile 修饰的静态实例变量
        
        // TODO: 私有化构造函数
        
        public static DclSingleton getInstance() {
            // TODO: 实现 DCL 双重检查锁逻辑
            // 提示：
            // 1. 第一层 if 检查
            // 2. synchronized 锁
            // 3. 第二层 if 检查
            // 4. 创建实例
            return null;
        }
    }

    @Test
    @DisplayName("题目2：验证DCL单例线程安全")
    @Timeout(10)
    void testDclSingletonThreadSafe() throws InterruptedException {
        final int threadCount = 100;
        Set<Integer> instanceCodes = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                try {
                    DclSingleton instance = DclSingleton.getInstance();
                    instanceCodes.add(System.identityHashCode(instance));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        assertEquals(1, instanceCodes.size(), 
            "DCL单例应该只有一个实例，但发现了 " + instanceCodes.size() + " 个");
    }

    // ==================== 题目3：静态内部类单例 ====================
    
    /**
     * 【题目3】实现静态内部类单例
     * 
     * 要求：
     * 1. 创建私有静态内部类 SingletonHolder
     * 2. 在内部类中创建外部类的静态 final 实例
     * 3. getInstance() 返回内部类的实例
     */
    public static class StaticInnerSingleton {
        // TODO: 私有化构造函数
        
        // TODO: 创建私有静态内部类 SingletonHolder
        // private static class SingletonHolder { ... }
        
        public static StaticInnerSingleton getInstance() {
            // TODO: 返回 SingletonHolder 中的实例
            return null;
        }
    }

    @Test
    @DisplayName("题目3：验证静态内部类单例")
    void testStaticInnerSingleton() {
        StaticInnerSingleton instance1 = StaticInnerSingleton.getInstance();
        StaticInnerSingleton instance2 = StaticInnerSingleton.getInstance();
        
        assertNotNull(instance1, "getInstance() 不应返回 null");
        assertSame(instance1, instance2, "两次调用应返回相同实例");
    }

    // ==================== 题目4：枚举单例 ====================
    
    /**
     * 【题目4】实现枚举单例
     * 
     * 要求：
     * 1. 定义枚举类型
     * 2. 只有一个枚举常量 INSTANCE
     * 3. 添加一个业务方法 doSomething()
     */
    public enum EnumSingleton {
        // TODO: 定义唯一的枚举常量 INSTANCE
        ;
        
        // TODO: 添加一个成员变量 name
        
        // TODO: 在构造函数中初始化 name
        
        /**
         * 业务方法
         * @return 返回 "EnumSingleton works!"
         */
        public String doSomething() {
            // TODO: 实现业务方法
            return null;
        }
    }

    @Test
    @DisplayName("题目4：验证枚举单例")
    void testEnumSingleton() {
        // 注意：如果枚举没有定义常量，这里会编译错误
        // 你需要先补充 INSTANCE 常量
        
        // 取消注释以下代码进行测试
        /*
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        
        assertSame(instance1, instance2, "枚举单例应该是同一个实例");
        assertEquals("EnumSingleton works!", instance1.doSomething(), 
            "doSomething() 应返回正确的字符串");
        */
        
        // 临时断言，完成代码后请删除
        fail("请完成枚举单例代码后，取消上方注释并删除此行");
    }

    // ==================== 附加题：防反射攻击 ====================
    
    /**
     * 【附加题】实现防反射攻击的单例
     * 
     * 要求：
     * 在构造函数中检测是否已存在实例，如果存在则抛出异常
     */
    public static class ReflectionSafeSingleton {
        private static final ReflectionSafeSingleton INSTANCE = new ReflectionSafeSingleton();
        
        private ReflectionSafeSingleton() {
            // TODO: 添加防反射攻击的代码
            // 提示：如果 INSTANCE 已经存在，抛出 RuntimeException
        }
        
        public static ReflectionSafeSingleton getInstance() {
            return INSTANCE;
        }
    }

    @Test
    @DisplayName("附加题：验证防反射攻击")
    void testReflectionSafe() {
        assertThrows(Exception.class, () -> {
            java.lang.reflect.Constructor<ReflectionSafeSingleton> constructor = 
                ReflectionSafeSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        }, "反射创建实例时应该抛出异常");
    }
}
