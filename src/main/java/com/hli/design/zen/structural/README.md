# 结构型模式（Structural Patterns）

> 📖 对应《设计模式之禅》第二篇：结构型模式

---

## 一、概述

结构型模式**关注类和对象的组合**，通过组合获得更大的结构，同时保持结构的灵活性和高效性。

> **核心思想**：通过组合而非继承来扩展功能，实现更灵活的对象结构

---

## 二、7种结构型模式总览

| 模式 | 核心思想 | 解决的问题 | 面试频率 |
|------|----------|------------|----------|
| 代理模式（Proxy） | 为对象提供代理 | 控制访问、增强功能 | ★★★★★ |
| 装饰器模式（Decorator） | 动态添加职责 | 功能增强、避免继承爆炸 | ★★★★★ |
| 适配器模式（Adapter） | 接口转换 | 兼容不同接口 | ★★★★★ |
| 门面模式（Facade） | 提供统一接口 | 简化复杂子系统 | ★★★★☆ |
| 组合模式（Composite） | 树形结构 | 部分-整体层次 | ★★★★☆ |
| 桥接模式（Bridge） | 分离抽象和实现 | 多维度扩展 | ★★★☆☆ |
| 享元模式（Flyweight） | 共享细粒度对象 | 减少对象数量 | ★★★☆☆ |

---

## 三、模式对比

### 3.1 相似模式区分

| 对比 | 代理模式 | 装饰器模式 | 适配器模式 |
|------|----------|------------|------------|
| 目的 | 控制访问 | 增强功能 | 接口转换 |
| 接口 | 与目标相同 | 与目标相同 | 不同接口 |
| 关系 | 持有目标引用 | 组合目标 | 包装目标 |
| 场景 | 权限、缓存、延迟 | 动态添加功能 | 系统集成 |

### 3.2 选型决策

```
需要控制对象访问？ ──是──▶ 代理模式（权限、缓存、远程）
      │
      否
      │
      ▼
需要动态增强功能？ ──是──▶ 装饰器模式（包装增强）
      │
      否
      │
      ▼
需要兼容不同接口？ ──是──▶ 适配器模式（接口转换）
      │
      否
      │
      ▼
需要简化复杂系统？ ──是──▶ 门面模式（统一入口）
      │
      否
      │
      ▼
需要处理树形结构？ ──是──▶ 组合模式（递归组合）
      │
      否
      ▼
需要减少对象数量？ ──是──▶ 享元模式（对象池）
```

---

## 四、目录结构规划

```
structural/
├── README.md               # 本说明文档
├── proxy/                  # 📋 代理模式（待实现）
│   ├── v1_static/          # 静态代理
│   ├── v2_dynamic_jdk/     # JDK动态代理
│   ├── v3_dynamic_cglib/   # CGLIB动态代理
│   └── v4_spring_aop/      # Spring AOP
├── decorator/              # 📋 装饰器模式（待实现）
│   ├── v1_bad/             # 反例：继承爆炸
│   └── v2_decorator/       # 装饰器实现
├── adapter/                # 📋 适配器模式（待实现）
│   ├── v1_class/           # 类适配器
│   └── v2_object/          # 对象适配器
├── facade/                 # 📋 门面模式（待实现）
├── composite/              # 📋 组合模式（待实现）
├── bridge/                 # 📋 桥接模式（待实现）
└── flyweight/              # 📋 享元模式（待实现）
```

---

## 五、面试高频问题

### Q1：代理模式和装饰器模式的区别？

> **答**：
> - **代理模式**：控制访问，代理类和目标类实现相同接口，但代理类决定是否调用目标
> - **装饰器模式**：增强功能，装饰器包装目标对象，透明地添加新功能
> 
> **简单记忆**：代理像保安（控制进入），装饰器像化妆（增强外表）

---

### Q2：JDK动态代理和CGLIB的区别？

| 特性 | JDK动态代理 | CGLIB |
|------|-------------|-------|
| 实现方式 | 基于接口 | 基于继承 |
| 要求 | 目标必须实现接口 | 目标类不能是final |
| 性能 | 生成快，调用慢 | 生成慢，调用快 |
| Spring默认 | 有接口时使用 | 无接口时使用 |

---

### Q3：适配器模式有哪两种实现？

| 类型 | 实现方式 | 优缺点 |
|------|----------|--------|
| 类适配器 | 继承被适配类 | 简单，但只能适配一个类 |
| 对象适配器 | 组合被适配对象 | 灵活，可适配多个类 |

**推荐**：对象适配器（组合优于继承）

---

## 六、实际项目应用

### 6.1 JDK 源码

| 模式 | 示例 |
|------|------|
| 代理 | `java.lang.reflect.Proxy` |
| 装饰器 | `BufferedInputStream` 装饰 `InputStream` |
| 适配器 | `InputStreamReader` 适配 `InputStream` 到 `Reader` |
| 门面 | `java.util.logging.Logger` |
| 组合 | `java.awt.Container` |
| 享元 | `Integer.valueOf()` 缓存 -128~127 |

### 6.2 Spring 源码

| 模式 | 示例 |
|------|------|
| 代理 | Spring AOP（JDK/CGLIB代理） |
| 装饰器 | `BeanWrapper`、`HttpServletRequestWrapper` |
| 适配器 | `HandlerAdapter`（适配不同Controller） |
| 门面 | `JdbcTemplate`（封装JDBC操作） |

### 6.3 大厂应用场景

| 模式 | 场景 |
|------|------|
| 代理 | RPC远程调用、权限校验、缓存 |
| 装饰器 | 日志增强、压缩/加密流 |
| 适配器 | 第三方SDK集成、老系统改造 |
| 门面 | 微服务网关、复杂业务封装 |
| 组合 | 组织架构树、菜单系统 |
| 享元 | 连接池、线程池 |

---

## 七、核心代码示例

### 7.1 代理模式（面试必写）

```java
// JDK动态代理
public interface UserService {
    void save(User user);
}

UserService proxy = (UserService) Proxy.newProxyInstance(
    UserService.class.getClassLoader(),
    new Class[]{UserService.class},
    (proxyObj, method, args) -> {
        log.info("前置增强|Before_advice");
        Object result = method.invoke(target, args);
        log.info("后置增强|After_advice");
        return result;
    }
);
```

### 7.2 装饰器模式

```java
// IO 流装饰
InputStream is = new FileInputStream("file.txt");
InputStream bis = new BufferedInputStream(is);      // 装饰：缓冲
InputStream dis = new DataInputStream(bis);         // 再装饰：数据处理
```

### 7.3 适配器模式

```java
// 对象适配器
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    @Override
    public void play(String filename) {
        advancedPlayer.playAdvanced(filename);  // 适配调用
    }
}
```

---

## 八、学习路线建议

```
1. 代理模式（Spring AOP 核心）
        ↓
2. 装饰器模式（IO 流设计）
        ↓
3. 适配器模式（系统集成）
        ↓
4. 门面模式（API 设计）
        ↓
5. 组合模式（树形结构）
        ↓
6. 桥接模式、享元模式（选学）
```

---

## 九、面试深挖追问

1. **Spring AOP 用的是什么代理？**
   - 有接口：JDK动态代理
   - 无接口：CGLIB
   - 可通过 `@EnableAspectJAutoProxy(proxyTargetClass = true)` 强制使用CGLIB

2. **装饰器模式和继承的区别？**
   - 继承是静态的，装饰器是动态的
   - 装饰器可以任意组合，避免类爆炸

3. **门面模式和API网关的关系？**
   - API网关就是门面模式的应用
   - 提供统一入口，隐藏后端微服务复杂性

---

> 📌 **学习建议**：代理模式是面试必问，务必手写 JDK 动态代理代码。装饰器和适配器要能区分应用场景。
