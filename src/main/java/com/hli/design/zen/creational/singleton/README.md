# 单例模式（Singleton Pattern）

> 📖 对应《设计模式之禅》第7章：单例模式

---

## 一、模式简介

**单例模式**确保一个类只有一个实例，并提供一个全局访问点。

> **核心思想**：私有化构造函数 + 类内部创建实例 + 提供静态获取方法

---

## 二、核心原理

### 2.1 UML 类图

```
┌─────────────────────────────────┐
│          Singleton              │
├─────────────────────────────────┤
│ - instance: Singleton           │
├─────────────────────────────────┤
│ - Singleton()                   │  ← 私有构造
│ + getInstance(): Singleton      │  ← 全局访问点
└─────────────────────────────────┘
```

### 2.2 实现方式对比

| 实现方式 | 线程安全 | 懒加载 | 防反射 | 防序列化 | 推荐度 |
|----------|----------|--------|--------|----------|--------|
| 饿汉式 | ✅ | ❌ | ❌ | ❌ | ★★★☆☆ |
| 懒汉式（无锁） | ❌ | ✅ | ❌ | ❌ | ★☆☆☆☆ |
| DCL双重检查锁 | ✅ | ✅ | ❌ | ❌ | ★★★★☆ |
| 静态内部类 | ✅ | ✅ | ❌ | ❌ | ★★★★★ |
| 枚举单例 | ✅ | ❌ | ✅ | ✅ | ★★★★★ |

---

## 三、代码实现

本目录包含 5 种单例实现：

### 3.1 v1_bad - 反例（线程不安全的懒汉式）

**文件**：[EmperorBad.java](./v1_bad/EmperorBad.java)

**问题**：多线程环境下可能创建多个实例

```java
// 致命问题：线程A和B同时进入if块，各自创建实例
if (instance == null) {
    instance = new EmperorBad();  // 非原子操作
}
```

**面试价值**：演示为什么需要同步机制

---

### 3.2 v2_dcl - DCL双重检查锁

**文件**：[EmperorDcl.java](./v2_dcl/EmperorDcl.java)

**关键点**：
1. `volatile` 禁止指令重排
2. 第一层检查：避免不必要的同步
3. 第二层检查：防止重复创建

```java
private static volatile EmperorDcl instance;

public static EmperorDcl getInstance() {
    if (instance == null) {           // 第一层检查
        synchronized (EmperorDcl.class) {
            if (instance == null) {   // 第二层检查
                instance = new EmperorDcl();
            }
        }
    }
    return instance;
}
```

---

### 3.3 v3_static_inner - 静态内部类（推荐）

**文件**：[EmperorStatic.java](./v3_static_inner/EmperorStatic.java)

**核心原理**：利用 JVM 类加载机制保证线程安全

```java
private static class SingletonHolder {
    private static final EmperorStatic INSTANCE = new EmperorStatic();
}

public static EmperorStatic getInstance() {
    return SingletonHolder.INSTANCE;  // 触发内部类加载
}
```

**优势**：
- 无锁，性能最优
- 懒加载
- 代码简洁

---

### 3.4 v4_enum - 枚举单例（最安全）

**文件**：[EmperorEnum.java](./v4_enum/EmperorEnum.java)

**核心原理**：JVM 层面保证唯一性

```java
public enum EmperorEnum {
    INSTANCE;
    
    public void doSomething() {
        // 业务方法
    }
}
```

**优势**：
- 天然防反射攻击
- 自动处理序列化
- 《Effective Java》推荐

---

### 3.5 v5_spring - Spring 单例

**说明**：Spring 容器管理的 Bean 默认是单例

```java
@Component  // 或 @Service, @Repository
public class MyService {
    // Spring 保证只创建一个实例
}
```

---

## 四、面试高频问题

### Q1：单例模式解决什么问题？

> **答**：保证一个类在整个应用中只有一个实例，常用于：
> - 配置管理（全局配置只需一份）
> - 连接池（避免重复创建连接）
> - 日志对象（统一日志输出）
> - 线程池（资源复用）

---

### Q2：DCL 为什么需要 volatile？

> **答**：防止指令重排序。`instance = new Singleton()` 实际分三步：
> 1. 分配内存空间
> 2. 初始化对象
> 3. 将 instance 指向内存地址
> 
> 如果发生重排序（1→3→2），其他线程可能拿到未初始化的对象。
> `volatile` 禁止这种重排序。

---

### Q3：如何破坏单例？如何防护？

| 攻击方式 | 破坏原理 | 防护方案 |
|----------|----------|----------|
| 反射攻击 | `setAccessible(true)` 调用私有构造 | 枚举单例（JVM禁止） |
| 序列化攻击 | 反序列化创建新对象 | 重写 `readResolve()` 返回单例 |
| 克隆攻击 | `clone()` 创建新对象 | 重写 `clone()` 抛异常或返回单例 |

**枚举单例的防反射原理**：
```java
// java.lang.reflect.Constructor#newInstance
if ((clazz.getModifiers() & Modifier.ENUM) != 0)
    throw new IllegalArgumentException("Cannot reflectively create enum objects");
```

---

### Q4：静态内部类为什么是线程安全的？

> **答**：JVM 类加载机制保证。根据《Java虚拟机规范》：
> - 类的初始化阶段是线程安全的
> - JVM 使用锁机制确保 `<clinit>()` 方法只执行一次
> - 静态变量在类初始化时赋值

---

### Q5：单例模式的缺点是什么？

| 缺点 | 说明 |
|------|------|
| 扩展困难 | 违反开闭原则，想换实现必须改代码 |
| 测试困难 | 全局状态难以 Mock |
| 隐藏依赖 | 调用方看不到依赖关系 |
| 并发问题 | 如果单例有可变状态，需要考虑线程安全 |

**最佳实践**：优先使用依赖注入（Spring）管理单例

---

## 五、实际项目应用

### 5.1 JDK 源码

| 类 | 说明 |
|----|------|
| `Runtime.getRuntime()` | 饿汉式单例 |
| `Desktop.getDesktop()` | 懒汉式单例 |

### 5.2 Spring 源码

| 类/注解 | 说明 |
|---------|------|
| `@Scope("singleton")` | Bean 默认单例 |
| `DefaultSingletonBeanRegistry` | 单例注册表 |
| `ApplicationContext` | 容器本身是单例 |

### 5.3 大厂应用场景

| 场景 | 说明 |
|------|------|
| 配置中心 | Nacos/Apollo 客户端 |
| 连接池 | HikariCP、Druid |
| 日志框架 | LoggerFactory |
| 线程池 | Executors 工具类 |
| 缓存管理 | Redis 连接管理 |

---

## 六、代码实践指导

### 6.1 何时使用单例？

✅ **适合使用**：
- 全局只需要一个实例的对象
- 频繁创建销毁会造成资源浪费
- 需要控制资源访问（如连接池）

❌ **不适合使用**：
- 有状态且状态会变化的对象
- 需要继承扩展的场景

### 6.2 推荐实现方式

| 场景 | 推荐实现 |
|------|----------|
| 普通业务类 | Spring `@Component` |
| 工具类 | 静态内部类 |
| 需要防反射 | 枚举单例 |
| 序列化场景 | 枚举单例 |

### 6.3 测试验证

运行测试验证线程安全性：

```bash
mvn test -Dtest=SingletonBenchmarkTest
```

测试文件：[SingletonBenchmarkTest.java](../../../../test/java/com/hli/design/zen/creational/singleton/SingletonBenchmarkTest.java)

---

## 七、面试深挖追问

> 面试官可能的连续追问：

1. **DCL 用 synchronized 锁类还是锁对象？为什么？**
   - 锁类。因为 instance 是静态变量，锁对象无法保护静态成员。

2. **volatile 除了禁止重排序，还有什么作用？**
   - 保证可见性。一个线程修改后，其他线程立即可见。

3. **Spring 的单例 Bean 是线程安全的吗？**
   - 容器只保证创建一个实例，不保证 Bean 内部线程安全。
   - 如果 Bean 有可变状态，需要自己处理同步。

4. **单例和静态方法有什么区别？**
   - 单例可以实现接口、继承父类
   - 单例可以延迟初始化
   - 单例可以被 Mock 测试

---

> 📌 **学习建议**：先理解反例代码的问题，再学习各种正确实现，最后结合 Spring 实战。
