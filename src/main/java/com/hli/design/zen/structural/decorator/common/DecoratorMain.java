package com.hli.design.zen.structural.decorator.common;

import com.hli.design.zen.structural.decorator.v2_standard.BlackCoffee;
import com.hli.design.zen.structural.decorator.v2_standard.Milk;
import com.hli.design.zen.structural.decorator.v2_standard.Sugar;
import com.hli.design.zen.structural.decorator.v3_architect.DrinkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 装饰器模式演示入口类
 *
 * <p>演示装饰器模式的三个版本演进：</p>
 * <ul>
 *     <li><b>V1 反模式：</b>通过继承实现功能组合，导致类爆炸</li>
 *     <li><b>V2 标准写法：</b>经典的"俄罗斯套娃"嵌套构造</li>
 *     <li><b>V3 架构师版本：</b>链式构建器，API 更优雅</li>
 * </ul>
 *
 * @author hli
 * @since 2026-01-30
 */
public class DecoratorMain {

    private static final Logger log = LoggerFactory.getLogger(DecoratorMain.class);

    /**
     * 程序入口
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {

        log.info("========== 装饰器模式演示 ==========");

        // ==================== V1 反模式演示 ====================
        log.info("");
        log.info("【V1_BAD】反模式：继承方式实现功能组合");
        log.info("--------------------------------------------");
        log.info("问题：每增加一种配料组合，就需要新增一个子类");
        log.info("  - Coffee（基类）");
        log.info("  - CoffeeWithMilk extends Coffee（加奶）");
        log.info("  - CoffeeWithSugar extends Coffee（加糖）");
        log.info("  - CoffeeWithMilkAndSugar extends Coffee（加奶加糖）");
        log.info("  - ... 😱 n种配料 → 2^n 个子类！");
        log.info("结论：继承导致类爆炸，维护噩梦！");

        // ==================== V2 标准写法演示 ====================
        log.info("");
        log.info("【V2_STANDARD】标准写法：装饰器模式（俄罗斯套娃）");
        log.info("--------------------------------------------");

        // 基础黑咖啡
        Drink blackCoffee = new BlackCoffee();
        log.info("基础饮品: {} = {}", blackCoffee.desc(), blackCoffee.cost());

        // 加奶
        Drink coffeeWithMilk = new Milk(new BlackCoffee());
        log.info("加奶: {} = {}", coffeeWithMilk.desc(), coffeeWithMilk.cost());

        // 加糖
        Drink coffeeWithSugar = new Sugar(new BlackCoffee());
        log.info("加糖: {} = {}", coffeeWithSugar.desc(), coffeeWithSugar.cost());

        // 加奶 + 加糖（套娃嵌套）
        Drink coffeeWithMilkAndSugar = new Sugar(new Milk(new BlackCoffee()));
        log.info("加奶加糖: {} = {}", coffeeWithMilkAndSugar.desc(), coffeeWithMilkAndSugar.cost());

        // 双倍奶 + 糖（可以重复装饰！）
        Drink doubleMilkWithSugar = new Sugar(new Milk(new Milk(new BlackCoffee())));
        log.info("双倍奶加糖: {} = {}", doubleMilkWithSugar.desc(), doubleMilkWithSugar.cost());

        log.info("优点：无需新增子类，通过组合动态扩展功能！");

        // ==================== V3 架构师版本演示 ====================
        log.info("");
        log.info("【V3_ARCHITECT】架构师版本：链式构建器");
        log.info("--------------------------------------------");

        Drink myDrink = new DrinkBuilder(new BlackCoffee())
                .with(Milk::new)
                .with(Sugar::new)
                .build();
        log.info("链式构建: {} = {}", myDrink.desc(), myDrink.cost());

        // 更复杂的组合
        Drink complexDrink = new DrinkBuilder(new BlackCoffee())
                .with(Milk::new)
                .with(Milk::new)  // 双倍奶
                .with(Sugar::new)
                .with(Sugar::new) // 双倍糖
                .build();
        log.info("复杂组合: {} = {}", complexDrink.desc(), complexDrink.cost());

        log.info("优点：API 更优雅，链式调用更直观！");

        log.info("");
        log.info("========== 演示结束 ==========");
    }
}
