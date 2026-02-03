package com.hli.design.zen.structural.flyweight.v1_simple;

import lombok.extern.slf4j.Slf4j;

/**
 * V1 烂代码：每次都创建新对象，内存浪费
 *
 * <p>问题：100万考生报名同一考场，会创建100万个对象，
 * 每个对象都存储相同的 location 和 subject 字符串</p>
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== V1 烂代码（内存浪费） ===");

        // 问题：每次 new 都创建新对象
        SignInfo s1 = new SignInfo("考生A", "北京一中", "申论");
        SignInfo s2 = new SignInfo("考生B", "北京一中", "申论");
        // s1 和 s2 存储了相同的 location="北京一中"，subject="申论"
        // 如果有100万考生，就有100万份重复数据！

        s1.display();
        s2.display();

        log.info("s1 == s2? {} (每次都是新对象！)\n", s1 == s2);
    }
}

/**
 * 报考信息类（烂代码版）
 *
 * <p>问题：没有区分内部状态和外部状态，所有数据都存在对象中</p>
 */
@Slf4j
class SignInfo {

    /**
     * 外部状态：每个考生不同
     */
    private String id;

    /**
     * 内部状态：很多考生相同，但每次都重复存储
     */
    private String location;
    private String subject;

    public SignInfo(String id, String location, String subject) {
        this.id = id;
        this.location = location;
        this.subject = subject;
        // 每次 new，都在堆里开辟新空间，存储重复的 location 字符串
    }

    public void display() {
        log.info("考生:{} 在 [{}] 考 [{}]", id, location, subject);
    }
}
