package com.hli.design.zen.behavioral.strategy.v2_standard;

import lombok.extern.slf4j.Slf4j;

/**
 * V2 标准策略模式演示：锦囊妙计的正确打开方式
 *
 * <p>诸葛亮把三条妙计分别装进三个锦囊，赵云根据场景打开对应的锦囊</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V2 标准策略模式 ===");
        log.info("核心：把每个妙计封装成独立的策略类，通过 Context 选择执行");

        // 场景一：刚到吴国，打开第一个锦囊
        IStrategy backDoor = new BackDoor();
        Context context1 = new Context(backDoor);
        context1.operate();

        // 场景二：乐不思蜀，打开第二个锦囊
        IStrategy greenLight = new GivenGreenLight();
        Context context2 = new Context(greenLight);
        context2.operate();

        // 场景三：被追杀，打开第三个锦囊
        IStrategy blockEnemy = new BlockEnemy();
        Context context3 = new Context(blockEnemy);
        context3.operate();

        log.info("");
    }
}

/**
 * 策略接口（锦囊接口）
 *
 * <p>每个锦囊里都有一条妙计</p>
 */
interface IStrategy {

    /**
     * 执行妙计
     */
    void operate();
}

/**
 * 具体策略一：找乔国老开后门
 *
 * <p>到达吴国后的第一条妙计</p>
 */
@Slf4j
class BackDoor implements IStrategy {

    @Override
    public void operate() {
        log.info("【锦囊一】找乔国老帮忙，让吴国太给孙权施压！");
    }
}

/**
 * 具体策略二：求吴国太开绿灯
 *
 * <p>刘备乐不思蜀时的妙计</p>
 */
@Slf4j
class GivenGreenLight implements IStrategy {

    @Override
    public void operate() {
        log.info("【锦囊二】求吴国太开绿灯，放行！");
    }
}

/**
 * 具体策略三：孙夫人断后
 *
 * <p>被追杀时的妙计</p>
 */
@Slf4j
class BlockEnemy implements IStrategy {

    @Override
    public void operate() {
        log.info("【锦囊三】孙夫人断后，挡住追兵！");
    }
}

/**
 * 上下文（锦囊袋子）
 *
 * <p>赵云手里拿的那个袋子，里面装什么计策，是诸葛亮放进去的</p>
 */
class Context {

    /**
     * 持有策略引用
     */
    private final IStrategy strategy;

    /**
     * 构造函数：设置要使用的妙计
     *
     * @param strategy 具体策略
     */
    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 赵云执行锦囊
     */
    public void operate() {
        this.strategy.operate();
    }
}
