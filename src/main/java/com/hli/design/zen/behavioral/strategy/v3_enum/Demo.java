package com.hli.design.zen.behavioral.strategy.v3_enum;

import lombok.extern.slf4j.Slf4j;

/**
 * V3 枚举策略演示：Effective Java 推荐的顶级写法
 *
 * <p>在实际开发中，如果策略无状态，用枚举 (Enum) 实现策略模式是最优雅的写法</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V3 枚举策略（架构师版本） ===");
        log.info("优势：枚举天生单例，线程安全，连 Context 都不需要！");

        // 直接通过枚举调用策略，无需 Context
        StrategyEnum.BACK_DOOR.execute();
        StrategyEnum.GREEN_LIGHT.execute();
        StrategyEnum.BLOCK_ENEMY.execute();

        log.info("\n场景驱动调用：");
        // 模拟根据场景选择策略
        executeByScenario("刚到吴国");
        executeByScenario("乐不思蜀");
        executeByScenario("被追杀");

        log.info("");
    }

    /**
     * 根据场景选择执行策略
     *
     * @param scenario 场景描述
     */
    private static void executeByScenario(String scenario) {
        StrategyEnum strategy = StrategyEnum.getByScenario(scenario);
        if (strategy != null) {
            strategy.execute();
        }
    }
}

/**
 * 枚举策略
 *
 * <p>定义枚举，同时实现抽象方法——每个枚举实例就是一个具体策略</p>
 *
 * <p>优势：
 * <ul>
 *   <li>枚举天生单例，线程安全</li>
 *   <li>代码简洁，无需额外的 Context 类</li>
 *   <li>Effective Java 推荐的写法</li>
 * </ul>
 * </p>
 */
@Slf4j
enum StrategyEnum {

    /**
     * 策略一：找乔国老开后门
     */
    BACK_DOOR("刚到吴国") {
        @Override
        public void execute() {
            log.info("【枚举策略一】找乔国老开后门");
        }
    },

    /**
     * 策略二：求吴国太开绿灯
     */
    GREEN_LIGHT("乐不思蜀") {
        @Override
        public void execute() {
            log.info("【枚举策略二】求吴国太开绿灯");
        }
    },

    /**
     * 策略三：孙夫人断后
     */
    BLOCK_ENEMY("被追杀") {
        @Override
        public void execute() {
            log.info("【枚举策略三】孙夫人断后");
        }
    };

    /**
     * 场景描述
     */
    private final String scenario;

    /**
     * 构造函数
     *
     * @param scenario 场景描述
     */
    StrategyEnum(String scenario) {
        this.scenario = scenario;
    }

    /**
     * 定义抽象方法让枚举实例去实现
     */
    public abstract void execute();

    /**
     * 根据场景获取对应策略
     *
     * @param scenario 场景描述
     * @return 对应的策略枚举，找不到返回 null
     */
    public static StrategyEnum getByScenario(String scenario) {
        for (StrategyEnum strategy : values()) {
            if (strategy.scenario.equals(scenario)) {
                return strategy;
            }
        }
        return null;
    }
}
