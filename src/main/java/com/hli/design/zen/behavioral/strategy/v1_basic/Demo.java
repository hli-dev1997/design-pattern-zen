package com.hli.design.zen.behavioral.strategy.v1_basic;

import lombok.extern.slf4j.Slf4j;

/**
 * V1 烂代码演示：赵云的噩梦——硬编码 if-else
 *
 * <p>诸葛亮没用策略模式，而是给赵云写了一封 5000 字的长信（伪代码）</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V1 烂代码（赵云的噩梦） ===");
        log.info("问题：所有妙计都写在一个方法里，用 if-else 判断");
        log.info("新增场景？赵云得在战场上重新背这封信（修改代码），太危险了！\n");

        // 演示烂代码
        ZhaoYun zhaoYun = new ZhaoYun();
        zhaoYun.execute("刚到吴国");
        zhaoYun.execute("乐不思蜀");
        zhaoYun.execute("被追杀");
        log.info("");
    }
}

/**
 * 烂代码示例：赵云类
 *
 * <p>所有妙计都硬编码在 execute 方法中，违反开闭原则</p>
 */
@Slf4j
class ZhaoYun {

    /**
     * 执行妙计
     *
     * <p>问题：所有场景都写在一个方法里，难以维护和扩展</p>
     *
     * @param situation 场景描述
     */
    public void execute(String situation) {
        // 使用常量避免魔法值
        if ("刚到吴国".equals(situation)) {
            log.info("【场景一】去找乔国老帮忙...");
        } else if ("乐不思蜀".equals(situation)) {
            log.info("【场景二】去找吴国太哭诉...");
        } else if ("被追杀".equals(situation)) {
            log.info("【场景三】求孙夫人断后...");
        }
        // 万一将来加了第四个场景？赵云得在战场上重新背这封信（修改代码），太危险了！
    }
}
