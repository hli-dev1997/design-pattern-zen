package com.hli.design.zen.creational.factory;

import com.hli.design.zen.creational.factory.common.BlackHuman;
import com.hli.design.zen.creational.factory.common.Human;
import com.hli.design.zen.creational.factory.common.WhiteHuman;
import com.hli.design.zen.creational.factory.common.YellowHuman;
import com.hli.design.zen.creational.factory.v1_bad.BlueHuman;
import com.hli.design.zen.creational.factory.v2_reflectionFactory.HumanReflectionFactory;
import com.hli.design.zen.creational.factory.v3_factory.AbstractHumanFactory;
import com.hli.design.zen.creational.factory.v3_factory.BlackHumanFactory;
import com.hli.design.zen.creational.factory.v3_factory.WhiteHumanFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 女娲类（客户端 Client）
 * <p>
 * 设计目的：
 * 1. 模拟场景中的调用者，演示如何使用工厂类创建对象。
 * 2. 验证简单工厂模式的功能。
 * <p>
 * 为什么需要该类：
 * 在设计模式中，Client 是不可或缺的角色。它代表了使用模式的业务代码。
 * 通过 NvWa 类，我们可以直观地看到工厂模式如何简化了客户端的对象创建过程。
 * <p>
 * 核心实现思路：
 * - 调用 HumanFactory.createHuman() 方法，传入不同的人种类型。
 * - 获取到 Human 对象后，调用其业务方法（getColor, talk）。
 */
@Slf4j
public class NvWa {

    /**
     * 女娲造人主程序
     * <p>
     * 实现逻辑：
     * 1. 第一次造人：火候不足，造出了白人。
     * 2. 第二次造人：火候太过了，造出了黑人。
     * 3. 第三次造人：火候刚刚好，造出了黄种人。
     * 4. 每次造完人，都让他们展示一下肤色和说话能力。
     */
    public static void main(String[] args) {
        log.info("===== 女娲开始造人 (简单工厂模式) =====");

        //todo v1_bad
        //思考如何通过反射避免createHuman("white")这种硬编码
//        // 1. 造白人
//        log.info("--- 第一次造人：火候不足 ---");
//        Human whiteHuman = HumanFactory.createHuman("white");
//        if (whiteHuman != null) {
//            whiteHuman.getColor();
//            whiteHuman.talk();
//        }
//        // 2. 造黑人
//        log.info("--- 第二次造人：火候太过了 ---");
//        Human blackHuman = HumanFactory.createHuman("black");
//        if (blackHuman != null) {
//            blackHuman.getColor();
//            blackHuman.talk();
//        }
//        // 3. 造黄种人
//        log.info("--- 第三次造人：火候刚刚好 ---");
//        Human yellowHuman = HumanFactory.createHuman("yellow");
//        if (yellowHuman != null) {
//            yellowHuman.getColor();
//            yellowHuman.talk();
//        }
//        // 4. 测试未知类型
//        log.info("--- 第四次造人：随便捏捏 ---");
//        Human unknownHuman = HumanFactory.createHuman("blue");
//        if (unknownHuman == null) {
//            log.warn("造人失败，没有这种人种|Creation_failed,type=blue");
//        }

//        //todo v2_v2_reflectionFactory
//        //思考危机降临：反射工厂的死穴，场景突变： 如果女娲娘娘说：“造人没这么简单！”,造白人：需要 new WhiteHuman()，然后 setLanguage("English")，还要 paint("White")。
//        //反射只能解决**“实例化”的通用性，解决不了“初始化逻辑差异化”**的问题。一旦初始化逻辑复杂，简单工厂（Simple Factory）必死无疑。
//        // 1. 造白人
//        log.info("--- 第一次造人：火候不足 ---");
//        Human whiteHuman = HumanReflectionFactory.createHuman(WhiteHuman.class);
//        if (whiteHuman != null) {
//            whiteHuman.getColor();
//            whiteHuman.talk();
//        }
//        // 2. 造黑人
//        log.info("--- 第二次造人：火候太过了 ---");
//        Human blackHuman = HumanReflectionFactory.createHuman(BlackHuman.class);
//        if (blackHuman != null) {
//            blackHuman.getColor();
//            blackHuman.talk();
//        }
//        // 3. 造黄种人
//        log.info("--- 第三次造人：火候刚刚好 ---");
//        Human yellowHuman = HumanReflectionFactory.createHuman(YellowHuman.class);
//        if (yellowHuman != null) {
//            yellowHuman.getColor();
//            yellowHuman.talk();
//        }
//        // 4. 测试未知类型
//        log.info("--- 第四次造人：随便捏捏 ---");
//        Human unknownHuman = HumanReflectionFactory.createHuman(null);
//        if (unknownHuman == null) {
//            log.warn("造人失败，没有这种人种|Creation_failed");
//        }
//        // 5. 额外需求蓝精灵
//        log.info("--- 扩展造人：额外需求蓝精灵 ---");
//        Human blueHuman = HumanReflectionFactory.createHuman(BlueHuman.class);
//        if (blueHuman != null) {
//            blueHuman.getColor();
//            blueHuman.talk();
//        }

        //todo 真正工厂模式
        // 1. 想造白人？先去把“白人八卦炉”搬出来
        AbstractHumanFactory whiteFactory = new WhiteHumanFactory();
        // 炉子开启，产出白人
        Human whiteHuman = whiteFactory.createHuman();
        if (whiteHuman != null) {
            whiteHuman.getColor();
            whiteHuman.talk();
        }

        // 2. 想造黑人？再去把“黑人八卦炉”搬出来
        AbstractHumanFactory blackFactory = new BlackHumanFactory();
        // 炉子开启，产出黑人（而且是已经初始化好语言的黑人）
        Human blackHuman = blackFactory.createHuman();
        if (blackHuman != null) {
            blackHuman.getColor();
            blackHuman.talk();
        }
        //黑人说|黑人说非洲话
        log.info("===== 女娲造人结束 =====");
    }
}
