package com.hli.design.zen.creational.factory.v1_bad;

import com.hli.design.zen.creational.factory.common.BlackHuman;
import com.hli.design.zen.creational.factory.common.Human;
import com.hli.design.zen.creational.factory.common.WhiteHuman;
import com.hli.design.zen.creational.factory.common.YellowHuman;
import lombok.extern.slf4j.Slf4j;

/**
 * 人类工厂类（简单工厂模式）
 *
 * 设计目的：
 * 1. 封装对象的创建过程，将对象的创建与使用解耦。
 * 2. 客户端只需要知道工厂类和产品类型，不需要关心具体的实现类。
 *
 * 为什么需要该类：
 * 如果没有工厂类，客户端代码中会充斥着大量的 new BlackHuman(), new WhiteHuman() 等代码。
 * 一旦需要修改具体实现类或者增加新的人种，就需要修改所有客户端代码，违反了开闭原则。
 * 使用工厂类后，只需要修改工厂类即可。
 *
 * 缺点：
 * 简单工厂模式违背了开闭原则（OCP）。如果需要增加新的人种（如 BrownHuman），
 * 就必须修改 HumanFactory 的 createHuman 方法，增加新的 case 分支。
 */
@Slf4j
public class HumanFactory {

    /**todo 思考怎么通过反射避开了硬编码的 if-else
     * 创建人类实例
     *
     * 实现逻辑：
     * 根据传入的类型字符串，返回对应的具体人类实例。
     * 使用 Java 14+ 的 switch 表达式，代码更简洁。
     *
     * @param type 人种类型 (white, black, yellow)
     * @return Human 具体人类实例，如果类型不匹配返回 null
     */
    public static Human createHuman(String type) {
        if (type == null) {
            return null;
        }
        return switch (type.toLowerCase()) {
            case "white" -> new WhiteHuman();
            case "black" -> new BlackHuman();
            case "yellow" -> new YellowHuman();
            default -> {
                log.warn("未知的人种类型|Unknown_human_type,type={}", type);
                yield null;
            }
        };
    }
}
