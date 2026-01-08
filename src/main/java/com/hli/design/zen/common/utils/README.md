# 工具类模块

> 📖 并发测试与通用工具

---

## 一、概述

本模块包含项目的通用工具类，主要用于：
- 并发测试支持
- 性能基准测试
- 通用工具方法

---

## 二、模块规划

```
utils/
├── README.md                   # 本说明文档
├── ConcurrencyTestHelper.java  # 📋 并发测试辅助类（待实现）
├── BenchmarkUtils.java         # 📋 基准测试工具（待实现）
└── ReflectionBreaker.java      # 📋 反射攻击测试（待实现）
```

---

## 三、并发测试工具设计

### 3.1 ConcurrencyTestHelper

用于测试单例模式的线程安全性：

```java
/**
 * 并发测试辅助类
 * 
 * 设计目的：
 * 1. 简化多线程测试代码编写
 * 2. 统一并发测试模式
 * 3. 提供结果收集和验证
 */
public class ConcurrencyTestHelper {
    
    /**
     * 执行并发测试
     * 
     * @param threadCount 并发线程数
     * @param task 每个线程执行的任务
     * @return 收集到的结果集合
     */
    public static <T> Set<T> runConcurrent(int threadCount, Supplier<T> task) {
        Set<T> results = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);
        
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();  // 等待统一起跑
                    results.add(task.get());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }
        
        startLatch.countDown();  // 发令枪
        endLatch.await();        // 等待结束
        return results;
    }
}
```

### 3.2 使用示例

```java
// 测试单例线程安全
Set<Integer> instances = ConcurrencyTestHelper.runConcurrent(
    100, 
    () -> System.identityHashCode(EmperorDcl.getInstance())
);

if (instances.size() > 1) {
    log.error("单例失败！创建了{}个实例", instances.size());
} else {
    log.info("单例成功！");
}
```

---

## 四、反射攻击测试

### 4.1 ReflectionBreaker

演示如何破坏单例模式：

```java
/**
 * 反射攻击演示类
 * 
 * 设计目的：
 * 1. 演示普通单例如何被反射破坏
 * 2. 对比枚举单例的防护能力
 * 3. 面试深挖必备知识点
 */
public class ReflectionBreaker {
    
    /**
     * 尝试通过反射破坏单例
     */
    public static <T> T breakSingleton(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);  // 💀 绕过私有访问
        return constructor.newInstance();
    }
    
    /**
     * 演示枚举的防护
     */
    public static void tryBreakEnum() {
        try {
            // 尝试破坏枚举单例
            Constructor<EmperorEnum> constructor = 
                EmperorEnum.class.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            constructor.newInstance("HACKER", 1);
        } catch (IllegalArgumentException e) {
            // ✅ 预期：Cannot reflectively create enum objects
            log.info("枚举防护成功！{}", e.getMessage());
        }
    }
}
```

---

## 五、与设计模式的关联

| 工具类 | 涉及模式 | 说明 |
|--------|----------|------|
| ConcurrencyTestHelper | 模板方法 | 定义测试骨架 |
| BenchmarkUtils | 策略模式 | 可切换测试策略 |
| ReflectionBreaker | 单例模式 | 验证单例安全性 |

---

## 六、面试相关知识点

### Q1：如何验证单例的线程安全性？

> 使用 `CountDownLatch` 让多线程同时起跑，收集所有返回的实例，检查是否唯一。

### Q2：反射能破坏哪些单例？

| 实现方式 | 能否被破坏 | 原因 |
|----------|------------|------|
| 懒汉式 | ✅ 能 | 私有构造可被 setAccessible |
| 饿汉式 | ✅ 能 | 同上 |
| DCL | ✅ 能 | 同上 |
| 静态内部类 | ✅ 能 | 同上 |
| 枚举 | ❌ 不能 | JVM 禁止反射创建枚举 |

### Q3：如何防护非枚举单例？

```java
private EmperorDcl() {
    if (instance != null) {
        throw new RuntimeException("单例已存在，禁止反射创建！");
    }
}
```

---

## 七、待实现代码清单

- [ ] `ConcurrencyTestHelper.java` - 并发测试辅助类
- [ ] `BenchmarkUtils.java` - 基准测试工具
- [ ] `ReflectionBreaker.java` - 反射攻击演示
- [ ] `SerializationBreaker.java` - 序列化攻击演示

---

> 📌 **学习建议**：理解并发测试的原理，以及如何验证设计模式的正确性。
