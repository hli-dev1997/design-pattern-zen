package com.hli.design.zen.creational.factory.v2_reflectionFactory;

import com.hli.design.zen.creational.factory.common.Human;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * 人类工厂类（反射通用版）
 *
 * 设计目的：
 * 1. 彻底消除 if-else/switch 逻辑，符合开闭原则（OCP）。
 * 2. 利用 Java 泛型 + 反射，实现“万能造人”。
 *
 * 核心优势：
 * - 以后新增 BlueHuman，只需写好类，直接传 Class 对象进来即可。
 * - 工厂类代码不需要修改一行！
 */
@Slf4j
public class HumanReflectionFactory {

    /**
     * 创建人类实例（万能方法）
     *
     * @param c 具体人种的 Class 对象 (必须是 Human 的子类)
     * @param <T> 泛型约束，必须继承自 Human
     * @return 具体的人类实例
     */
    public static <T extends Human> T createHuman(Class<T> c) {
        T human = null;
        try {
            if (c != null) {
                // 🛑 核心黑科技：利用反射调用无参构造函数
                // 相当于在运行时动态执行：new WhiteHuman()
                human = c.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | 
                 IllegalAccessException | InvocationTargetException e) {
            log.error("造人失败！八卦炉参数错误或构造函数无法访问|Creation_error", e);
        }
        return human;
    }
}