# 桥接模式 (Bridge Pattern)

> 📖 对应《设计模式之禅》第29章：桥接模式

---

## 一、概述

**桥接模式**（Bridge Pattern）将抽象部分与实现部分分离，使它们可以独立变化。通过组合（而非继承）建立两个独立变化维度之间的关系。

> **核心思想**：用组合代替继承，解决多维度变化导致的类爆炸问题

---

## 二、角色定义

| 角色 | 说明 |
|------|------|
| Abstraction（抽象化角色） | 定义高层抽象接口，持有 Implementor 引用 |
| RefinedAbstraction（扩展抽象化） | 扩展 Abstraction，实现更详细的业务逻辑 |
| Implementor（实现化角色） | 定义实现类的接口，不必与 Abstraction 一致 |
| ConcreteImplementor（具体实现化） | Implementor 的具体实现 |

---

## 三、为什么需要桥接模式

假设有 M 种抽象和 N 种实现：
- **使用继承**：需要 M × N 个类（类爆炸）
- **使用桥接**：只需要 M + N 个类

---

## 四、版本演进

- `common` - 公共组件：实现化角色接口
- `v1_basic` - 基础桥接：抽象与实现分离的基本实现
- `v2_multi` - 多维桥接：多个维度独立变化的扩展示例

---

## 五、类图

```
┌─────────────────┐         ┌─────────────────┐
│   Abstraction   │◇───────>│   Implementor   │
├─────────────────┤         │   (interface)   │
│ -impl: Impl     │         ├─────────────────┤
│ +operation()    │         │ +operationImpl()│
└─────────────────┘         └─────────────────┘
        △                           △
        │                           │
┌───────┴────────┐          ┌───────┴────────┐
│RefinedAbstract │          │ConcreteImplA   │
├────────────────┤          ├────────────────┤
│ +operation()   │          │ +operationImpl()│
│ +otherMethod() │          └────────────────┘
└────────────────┘                  △
                                    │
                            ┌───────┴────────┐
                            │ConcreteImplB   │
                            ├────────────────┤
                            │ +operationImpl()│
                            └────────────────┘
```

---

## 六、经典案例：消息发送系统

### 问题场景
- **消息类型**：普通消息、加急消息、特急消息
- **发送方式**：短信、邮件、站内信

使用继承需要 3 × 3 = 9 个类，使用桥接只需要 3 + 3 = 6 个类。

### 结构说明
```
Message（Abstraction）
├── 持有 MessageSender（Implementor）的引用
├── CommonMessage（RefinedAbstraction）
├── UrgentMessage（RefinedAbstraction）
└── SuperUrgentMessage（RefinedAbstraction）

MessageSender（Implementor）
├── SmsSender（ConcreteImplementor）
├── EmailSender（ConcreteImplementor）
└── AppPushSender（ConcreteImplementor）
```

---

## 七、适用场景

1. **多维度变化**：系统有两个或多个独立变化的维度
2. **避免继承泛滥**：继承会导致类数量爆炸性增长
3. **运行时切换实现**：需要在运行时动态切换实现
4. **跨平台实现**：抽象层定义接口，不同平台提供实现

---

## 八、JDK & Spring 应用

| 框架 | 类 | 说明 |
|------|-----|------|
| JDK | `java.sql.Driver` | 数据库驱动接口，不同厂商提供实现 |
| JDK | `java.util.logging.Handler` | 日志处理器抽象 |
| JDBC | `DriverManager` / `Connection` | 抽象与实现分离 |
| SLF4J | 日志门面 | 接口与实现（Logback, Log4j）分离 |
| Spring | `ViewResolver` | 视图解析抽象 |

---

## 九、与其他模式对比

| 模式 | 目的 | 使用时机 |
|------|------|----------|
| 桥接模式 | 抽象与实现分离 | 设计之初，预防类爆炸 |
| 适配器模式 | 接口转换 | 设计之后，兼容老接口 |
| 策略模式 | 算法互换 | 行为可切换，单一维度 |
| 抽象工厂模式 | 创建产品族 | 可配合桥接创建实现类 |

---

## 十、面试常见问题

### Q1：桥接模式解决什么问题？

> **答**：解决多维度变化导致的类爆炸问题。将抽象和实现分离，使两个维度可以独立变化。

### Q2：桥接模式和策略模式的区别？

> **答**：
> - **桥接模式**：侧重结构，解耦抽象和实现，两个维度都可扩展
> - **策略模式**：侧重行为，算法互换，抽象层不变

### Q3：JDBC 是如何使用桥接模式的？

> **答**：
> - `DriverManager` + `Connection` 是抽象层
> - 各数据库厂商的 `Driver` 实现是具体实现层
> - 应用程序只依赖 JDBC 接口，不依赖具体数据库

---

> 📌 **学习建议**：桥接模式的核心是"解耦两个维度"，重点理解与适配器模式的区别：桥接是设计之初的解耦，适配器是设计之后的兼容。
