# 配置类模块

> 📖 工程级配置实践与设计模式应用

---

## 一、概述

本模块包含项目的核心配置类，体现了**工程级配置管理**的最佳实践。

配置类不仅是功能代码，本身也运用了多种设计模式。

---

## 二、模块文件

| 文件 | 说明 | 涉及模式 |
|------|------|----------|
| `ThreadPoolConfig.java` | 线程池配置 | 建造者模式、策略模式 |

---

## 三、ThreadPoolConfig 详解

### 3.1 设计目的

1. **统一管理**：集中配置多种类型的线程池
2. **场景区分**：IO密集型、CPU密集型、混合型、虚拟线程
3. **优雅关闭**：应用停止时确保任务完成
4. **监控支持**：提供线程池状态获取方法

### 3.2 涉及的设计模式

#### 建造者模式（Builder Pattern）

`ThreadPoolTaskExecutor` 的配置采用链式调用：

```java
executor.setCorePoolSize(corePoolSize);
executor.setMaxPoolSize(maxPoolSize);
executor.setQueueCapacity(queueCapacity);
executor.setKeepAliveSeconds(keepAliveSeconds);
executor.setThreadNamePrefix("io-task-");
```

#### 策略模式（Strategy Pattern）

拒绝策略可配置：

```java
// 调用者运行策略：保证任务不丢失
executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

// 直接拒绝策略：快速失败
executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
```

#### 单例模式（Singleton Pattern）

通过 `@Bean` 注解，Spring 保证每个线程池只创建一次：

```java
@Bean("ioTaskExecutor")
public ThreadPoolTaskExecutor ioTaskExecutor() { ... }
```

---

## 四、线程池配置最佳实践

### 4.1 核心参数计算

| 任务类型 | 核心线程数 | 最大线程数 | 公式 |
|----------|------------|------------|------|
| IO密集型 | CPU × 4 | CPU × 8 | `CPU × (1 + IO时间/CPU时间)` |
| CPU密集型 | CPU + 1 | CPU × 2 | 避免过多线程切换 |
| 混合型 | CPU × 2 | CPU × 4 | 平衡IO和CPU |

### 4.2 拒绝策略选择

| 策略 | 说明 | 适用场景 |
|------|------|----------|
| `AbortPolicy` | 抛异常 | 快速失败，及时发现问题 |
| `CallerRunsPolicy` | 调用者执行 | 保证任务不丢失 |
| `DiscardPolicy` | 静默丢弃 | 可接受任务丢失 |
| `DiscardOldestPolicy` | 丢弃最老任务 | 新任务优先 |

### 4.3 面试高频问题

**Q1：线程池参数如何设置？**

> 答：根据任务类型设置：
> - IO密集型：`coreSize = CPU × 2 ~ 4`
> - CPU密集型：`coreSize = CPU + 1`
> - 实际需要压测调优

**Q2：线程池如何实现优雅关闭？**

```java
executor.setWaitForTasksToCompleteOnShutdown(true);
executor.setAwaitTerminationSeconds(60);
```

**Q3：@Async 注解如何指定线程池？**

```java
@Async("ioTaskExecutor")  // 指定使用的线程池Bean名称
public void asyncMethod() { ... }
```

---

## 五、工程实践建议

### 5.1 配置外部化

```java
@Value("${async.executor.queue.capacity:200}")
private Integer queueCapacity;
```

配置写在 `application.yml`：

```yaml
async:
  executor:
    queue:
      capacity: 200
    keep:
      alive:
        seconds: 60
```

### 5.2 监控指标暴露

```java
public String getExecutorInfo(ThreadPoolTaskExecutor executor) {
    ThreadPoolExecutor pool = executor.getThreadPoolExecutor();
    return String.format(
        "核心:%d, 最大:%d, 当前:%d, 活跃:%d, 队列:%d",
        pool.getCorePoolSize(),
        pool.getMaximumPoolSize(),
        pool.getPoolSize(),
        pool.getActiveCount(),
        pool.getQueue().size()
    );
}
```

### 5.3 虚拟线程（Java 21+）

```java
@Bean("virtualThreadExecutor")
public Executor virtualThreadExecutor() {
    return Executors.newVirtualThreadPerTaskExecutor();
}
```

**优势**：
- 轻量级，创建成本极低
- 适合大量并发IO操作
- 无需手动管理线程数

---

## 六、代码实践指导

### 6.1 何时需要自定义线程池？

✅ **需要**：
- 异步任务处理
- 批量数据处理
- 延迟任务执行
- 定时任务

❌ **不需要**：
- 简单同步操作
- Spring 已提供的功能（如 `@Scheduled`）

### 6.2 注意事项

1. **避免使用 Executors 工厂方法**：可能导致 OOM
2. **设置有意义的线程名前缀**：便于日志排查
3. **配置优雅关闭**：避免任务丢失
4. **监控线程池状态**：及时发现资源耗尽

---

> 📌 **学习建议**：线程池配置是面试高频topic，务必理解参数含义和调优思路。
