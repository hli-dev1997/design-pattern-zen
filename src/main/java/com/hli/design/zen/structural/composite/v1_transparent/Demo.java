package com.hli.design.zen.structural.composite.v1_transparent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 透明模式演示
 *
 * @author hli
 * @since 1.0.0
 */
@Slf4j
public class Demo {

    public static void run() {
        log.info("=== 透明模式 ===");

        Folder root = new Folder("根目录");
        root.add(new Folder("文档"));
        root.add(new File("README.txt"));

        // 统一操作：无需区分类型
        Component component = root;
        component.display();

        // 叶子节点调用 add 抛异常
        try {
            new File("test.txt").add(null);
        } catch (UnsupportedOperationException e) {
            log.warn("透明模式缺点: {}", e.getMessage());
        }
        log.info("");
    }
}

/**
 * 透明模式 - 抽象组件
 *
 * <p>抽象组件声明所有操作，叶子节点对不支持的操作抛出异常</p>
 */
@Slf4j
abstract class Component {

    protected String name;

    protected Component(String name) {
        this.name = name;
    }

    /** 显示节点信息 */
    public abstract void display();

    /** 添加子节点（默认抛异常） */
    public void add(Component component) {
        throw new UnsupportedOperationException("叶子节点不支持添加操作");
    }

    /** 移除子节点（默认抛异常） */
    public void remove(Component component) {
        throw new UnsupportedOperationException("叶子节点不支持移除操作");
    }
}

/**
 * 透明模式 - 叶子节点: 文件
 */
@Slf4j
class File extends Component {

    public File(String name) {
        super(name);
    }

    @Override
    public void display() {
        log.info("📄 文件: {}", name);
    }
}

/**
 * 透明模式 - 树枝节点: 文件夹
 */
@Slf4j
class Folder extends Component {

    private final List<Component> children = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void display() {
        log.info("📂 文件夹: {}", name);
        children.forEach(Component::display);
    }
}
