package com.hli.design.zen.structural.composite.v3;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 高级版本演示
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== 高级版本 ===");

        Folder root = new Folder("项目")
                .add(new File("pom.xml"))
                .add(new Folder("src")
                        .add(new File("App.java")));

        root.printTree(0);
        log.info("节点总数: {}", root.countNodes());
    }
}

/**
 * 高级版本 - 抽象基类
 *
 * <p>支持树形打印和节点统计</p>
 */
@Slf4j
abstract class Node {

    protected String name;

    protected Node(String name) {
        this.name = name;
    }

    /** 显示节点信息 */
    public abstract void display();

    /** 打印树形结构 */
    public abstract void printTree(int depth);

    /** 统计节点数 */
    public abstract int countNodes();

    protected String indent(int depth) {
        return "    ".repeat(depth);
    }
}

/**
 * 高级版本 - 叶子节点: 文件
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

    @Override
    public void printTree(int depth) {
        log.info("{}📄 {}", indent(depth), name);
    }

    @Override
    public int countNodes() {
        return 1;
    }
}

/**
 * 高级版本 - 树枝节点: 文件夹
 *
 * <p>支持链式调用</p>
 */
@Slf4j
class Folder extends Node {

    private final List<Node> children = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public Folder add(Node node) {
        children.add(node);
        return this;
    }

    @Override
    public void display() {
        log.info("📂 文件夹: {}", name);
        children.forEach(Node::display);
    }

    @Override
    public void printTree(int depth) {
        log.info("{}📂 {}", indent(depth), name);
        children.forEach(node -> node.printTree(depth + 1));
    }

    @Override
    public int countNodes() {
        return 1 + children.stream().mapToInt(Node::countNodes).sum();
    }
}
