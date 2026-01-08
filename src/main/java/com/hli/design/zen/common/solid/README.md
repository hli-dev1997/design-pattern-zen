# SOLID 原则

> 📖 面向对象设计的五大核心原则，是理解设计模式的基础

---

## 一、概述

SOLID 是五个面向对象设计原则的首字母缩写，由 Robert C. Martin（Bob 大叔）提出。

**设计模式是 SOLID 原则的具体实践**，理解 SOLID 是学习设计模式的前提。

---

## 二、五大原则总览

| 原则 | 英文全称 | 核心思想 | 面试频率 |
|------|----------|----------|----------|
| **S** | Single Responsibility | 单一职责 | ★★★★★ |
| **O** | Open/Closed | 开闭原则 | ★★★★★ |
| **L** | Liskov Substitution | 里氏替换 | ★★★★☆ |
| **I** | Interface Segregation | 接口隔离 | ★★★☆☆ |
| **D** | Dependency Inversion | 依赖倒置 | ★★★★★ |

---

## 三、原则详解

### 3.1 单一职责原则（SRP）

> **一个类只应该有一个引起它变化的原因**

**反例**：
```java
// ❌ 一个类承担多个职责
public class UserService {
    public void saveUser(User user) { ... }      // 业务逻辑
    public void sendEmail(User user) { ... }     // 邮件发送
    public void generateReport(User user) { ... } // 报表生成
}
```

**正例**：
```java
// ✅ 职责分离
public class UserService {
    public void saveUser(User user) { ... }
}

public class EmailService {
    public void sendEmail(User user) { ... }
}

public class ReportService {
    public void generateReport(User user) { ... }
}
```

**关联设计模式**：门面模式、代理模式

---

### 3.2 开闭原则（OCP）

> **对扩展开放，对修改关闭**

**反例**：
```java
// ❌ 每次新增支付方式都要修改代码
public void pay(String type, BigDecimal amount) {
    if ("alipay".equals(type)) {
        // 支付宝
    } else if ("wechat".equals(type)) {
        // 微信
    } else if ("bank".equals(type)) {
        // 银行卡
    }
    // 新增支付方式要改这里...
}
```

**正例**：
```java
// ✅ 通过扩展新增，无需修改原有代码
public interface PaymentStrategy {
    void pay(BigDecimal amount);
}

// 新增支付方式只需实现接口
public class CryptoPayment implements PaymentStrategy {
    @Override
    public void pay(BigDecimal amount) { ... }
}
```

**关联设计模式**：策略模式、工厂模式、装饰器模式

---

### 3.3 里氏替换原则（LSP）

> **子类必须能够替换其父类**

**反例**：
```java
// ❌ 子类改变了父类的行为
public class Rectangle {
    protected int width, height;
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
}

public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = this.height = width;  // 违反LSP
    }
}
```

**正例**：
```java
// ✅ 使用组合而非继承，或重新设计继承体系
public interface Shape {
    int getArea();
}

public class Rectangle implements Shape { ... }
public class Square implements Shape { ... }
```

**关联设计模式**：模板方法模式

---

### 3.4 接口隔离原则（ISP）

> **客户端不应该被迫依赖它不使用的接口**

**反例**：
```java
// ❌ 胖接口
public interface Worker {
    void work();
    void eat();
    void sleep();
}

// 机器人不需要 eat 和 sleep
public class Robot implements Worker {
    void eat() { throw new UnsupportedOperationException(); }  // 被迫实现
    void sleep() { throw new UnsupportedOperationException(); }
}
```

**正例**：
```java
// ✅ 接口分离
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public class Robot implements Workable {
    void work() { ... }  // 只实现需要的
}

public class Human implements Workable, Eatable {
    void work() { ... }
    void eat() { ... }
}
```

**关联设计模式**：适配器模式

---

### 3.5 依赖倒置原则（DIP）

> **高层模块不应该依赖低层模块，两者都应该依赖抽象**

**反例**：
```java
// ❌ 高层直接依赖低层
public class OrderService {
    private MySQLOrderDao dao = new MySQLOrderDao();  // 依赖具体实现
}
```

**正例**：
```java
// ✅ 依赖抽象
public class OrderService {
    private OrderDao dao;  // 依赖接口
    
    public OrderService(OrderDao dao) {
        this.dao = dao;  // 依赖注入
    }
}

// Spring 实现
@Service
public class OrderService {
    @Autowired
    private OrderDao dao;  // Spring 自动注入
}
```

**关联设计模式**：工厂模式、依赖注入

---

## 四、面试高频问题

### Q1：SOLID 中最重要的是哪个？

> **答**：**开闭原则（OCP）** 是核心，其他原则都是为了实现开闭原则：
> - 单一职责：使修改影响范围最小
> - 里氏替换：保证子类可替换父类
> - 接口隔离：减少不必要的依赖
> - 依赖倒置：通过抽象实现扩展

---

### Q2：依赖注入和依赖倒置的关系？

| 概念 | 说明 |
|------|------|
| 依赖倒置 | 设计原则，依赖抽象而非具体 |
| 依赖注入 | 实现技术，通过构造/Setter/字段注入 |

**依赖注入是依赖倒置的实现手段**

---

### Q3：如何判断一个类是否违反单一职责？

**判断标准**：
1. 这个类有多少个"变化的原因"？
2. 这个类需要多少个测试类？
3. 修改一个功能是否会影响其他功能？

**经验法则**：一个类超过 200 行，大概率违反了 SRP

---

## 五、与设计模式的关联

| 原则 | 相关设计模式 |
|------|--------------|
| SRP | 门面模式、代理模式、策略模式 |
| OCP | 策略模式、装饰器模式、工厂模式 |
| LSP | 模板方法模式、组合模式 |
| ISP | 适配器模式、外观模式 |
| DIP | 工厂模式、抽象工厂、依赖注入 |

---

## 六、代码实践规划

```
solid/
├── README.md                  # 本说明文档
├── srp/                       # 📋 单一职责示例（待实现）
│   ├── v1_bad/                # 反例
│   └── v2_good/               # 正例
├── ocp/                       # 📋 开闭原则示例（待实现）
├── lsp/                       # 📋 里氏替换示例（待实现）
├── isp/                       # 📋 接口隔离示例（待实现）
└── dip/                       # 📋 依赖倒置示例（待实现）
```

---

## 七、面试深挖追问

1. **Spring 是如何体现依赖倒置的？**
   - Bean 之间通过接口依赖
   - 运行时由容器注入具体实现
   - `@Autowired` + 接口类型

2. **单一职责和高内聚的关系？**
   - 单一职责是设计原则
   - 高内聚是衡量结果
   - 遵循 SRP 自然得到高内聚

3. **过度设计怎么判断？**
   - 当前没有扩展需求却做了大量抽象
   - 代码可读性因抽象而下降
   - YAGNI 原则：You Ain't Gonna Need It

---

> 📌 **学习建议**：SOLID 是面试必问，务必能说出每个原则的定义、反例、正例，以及与设计模式的关联。
