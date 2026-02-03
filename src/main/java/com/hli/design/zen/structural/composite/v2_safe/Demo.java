package com.hli.design.zen.structural.composite.v2_safe;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全模式演示
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== 安全模式 ===");

        Folder root = new Folder("根目录");
        root.add(new Folder("视频"));
        root.add(new File("photo.jpg"));

        root.display();
        // File 没有 add 方法，编译时就能发现错误
        log.info("");
    }
}

/**
 * 安全模式 - 抽象组件
 *
 * <p>仅声明共有操作，管理子节点的方法在树枝节点中定义</p>
 */
@Slf4j
abstract class Node {

    protected String name;

    protected Node(String name) {
        this.name = name;
    }

    /** 显示节点信息 */
    public abstract void display();
}

/**
 * 安全模式 - 叶子节点: 文件
 */
@Slf4j
class File extends Node {

    public File(String name) {
        super(name);
    }

    @Override
    public void display() {
        log.info("📄 文件: {}", name);
    }
}

/**
 * 安全模式 - 树枝节点: 文件夹
 *
 * <p>管理子节点的方法仅在此类定义</p>
 */
@Slf4j
class Folder extends Node {

    private final List<Node> children = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public void add(Node node) {
        children.add(node);
    }

    public void remove(Node node) {
        children.remove(node);
    }

    @Override
    public void display() {
        log.info("📂 文件夹: {}", name);
        children.forEach(Node::display);
    }
}
