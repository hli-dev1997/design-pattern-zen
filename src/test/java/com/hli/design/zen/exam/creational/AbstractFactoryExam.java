package com.hli.design.zen.exam.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【抽象工厂模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共3道题，满分100分
 * 2. 需要在标记 TODO 的地方补充代码
 * 3. 场景：实现跨平台UI组件工厂（Windows/Mac）
 *
 * 验收标准：
 * - 题目1：定义产品族接口（Button、TextField）
 * - 题目2：实现抽象工厂和具体工厂
 * - 题目3：使用工厂创建产品族
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("抽象工厂模式考卷")
public class AbstractFactoryExam {

    // ==================== 题目1：定义产品接口 ====================
    
    /**
     * 【题目1-1】按钮接口
     */
    public interface Button {
        /**
         * 渲染按钮
         * @return 渲染结果描述
         */
        String render();
        
        /**
         * 点击按钮
         * @return 点击效果描述
         */
        String click();
    }
    
    /**
     * 【题目1-2】文本框接口
     */
    public interface TextField {
        /**
         * 渲染文本框
         * @return 渲染结果描述
         */
        String render();
        
        /**
         * 输入文本
         * @param text 输入的文本
         * @return 输入结果描述
         */
        String input(String text);
    }
    
    // ==================== Windows 产品实现 ====================
    
    /**
     * Windows 风格按钮
     * TODO: 实现 Button 接口
     */
    public static class WindowsButton implements Button {
        @Override
        public String render() {
            // TODO: 返回 "Rendering Windows Button"
            return null;
        }
        
        @Override
        public String click() {
            // TODO: 返回 "Windows Button clicked"
            return null;
        }
    }
    
    /**
     * Windows 风格文本框
     * TODO: 实现 TextField 接口
     */
    public static class WindowsTextField implements TextField {
        @Override
        public String render() {
            // TODO: 返回 "Rendering Windows TextField"
            return null;
        }
        
        @Override
        public String input(String text) {
            // TODO: 返回 "Windows TextField input: " + text
            return null;
        }
    }
    
    // ==================== Mac 产品实现 ====================
    
    /**
     * Mac 风格按钮
     * TODO: 实现 Button 接口
     */
    public static class MacButton implements Button {
        @Override
        public String render() {
            // TODO: 返回 "Rendering Mac Button"
            return null;
        }
        
        @Override
        public String click() {
            // TODO: 返回 "Mac Button clicked"
            return null;
        }
    }
    
    /**
     * Mac 风格文本框
     * TODO: 实现 TextField 接口
     */
    public static class MacTextField implements TextField {
        @Override
        public String render() {
            // TODO: 返回 "Rendering Mac TextField"
            return null;
        }
        
        @Override
        public String input(String text) {
            // TODO: 返回 "Mac TextField input: " + text
            return null;
        }
    }

    @Test
    @DisplayName("题目1：验证产品实现")
    void testProducts() {
        // Windows 产品
        Button winButton = new WindowsButton();
        TextField winTextField = new WindowsTextField();
        
        assertEquals("Rendering Windows Button", winButton.render());
        assertEquals("Windows Button clicked", winButton.click());
        assertEquals("Rendering Windows TextField", winTextField.render());
        assertEquals("Windows TextField input: hello", winTextField.input("hello"));
        
        // Mac 产品
        Button macButton = new MacButton();
        TextField macTextField = new MacTextField();
        
        assertEquals("Rendering Mac Button", macButton.render());
        assertEquals("Mac Button clicked", macButton.click());
        assertEquals("Rendering Mac TextField", macTextField.render());
        assertEquals("Mac TextField input: hello", macTextField.input("hello"));
    }

    // ==================== 题目2：抽象工厂 ====================
    
    /**
     * 【题目2-1】抽象工厂接口
     * 
     * 要求：定义创建 Button 和 TextField 的方法
     */
    public interface GUIFactory {
        // TODO: 定义创建 Button 的方法
        Button createButton();
        
        // TODO: 定义创建 TextField 的方法
        TextField createTextField();
    }
    
    /**
     * 【题目2-2】Windows 工厂
     * TODO: 实现 GUIFactory，创建 Windows 风格的组件
     */
    public static class WindowsFactory implements GUIFactory {
        @Override
        public Button createButton() {
            // TODO: 返回 WindowsButton
            return null;
        }
        
        @Override
        public TextField createTextField() {
            // TODO: 返回 WindowsTextField
            return null;
        }
    }
    
    /**
     * 【题目2-3】Mac 工厂
     * TODO: 实现 GUIFactory，创建 Mac 风格的组件
     */
    public static class MacFactory implements GUIFactory {
        @Override
        public Button createButton() {
            // TODO: 返回 MacButton
            return null;
        }
        
        @Override
        public TextField createTextField() {
            // TODO: 返回 MacTextField
            return null;
        }
    }

    @Test
    @DisplayName("题目2：验证抽象工厂")
    void testAbstractFactory() {
        // Windows 工厂
        GUIFactory winFactory = new WindowsFactory();
        Button winButton = winFactory.createButton();
        TextField winTextField = winFactory.createTextField();
        
        assertTrue(winButton instanceof WindowsButton, "应创建 WindowsButton");
        assertTrue(winTextField instanceof WindowsTextField, "应创建 WindowsTextField");
        
        // Mac 工厂
        GUIFactory macFactory = new MacFactory();
        Button macButton = macFactory.createButton();
        TextField macTextField = macFactory.createTextField();
        
        assertTrue(macButton instanceof MacButton, "应创建 MacButton");
        assertTrue(macTextField instanceof MacTextField, "应创建 MacTextField");
    }

    // ==================== 题目3：客户端使用 ====================
    
    /**
     * 【题目3】实现应用程序类
     * 
     * 要求：
     * 1. 通过构造函数接收 GUIFactory
     * 2. 使用工厂创建按钮和文本框
     * 3. renderUI() 返回所有组件的渲染结果
     */
    public static class Application {
        private Button button;
        private TextField textField;
        
        public Application(GUIFactory factory) {
            // TODO: 使用工厂创建按钮和文本框
        }
        
        /**
         * 渲染UI
         * @return 格式："Button: {button.render()}, TextField: {textField.render()}"
         */
        public String renderUI() {
            // TODO: 返回渲染结果
            return null;
        }
    }

    @Test
    @DisplayName("题目3：验证客户端使用")
    void testApplication() {
        // Windows 应用
        Application winApp = new Application(new WindowsFactory());
        String winResult = winApp.renderUI();
        assertTrue(winResult.contains("Windows Button"), "应包含 Windows Button");
        assertTrue(winResult.contains("Windows TextField"), "应包含 Windows TextField");
        
        // Mac 应用
        Application macApp = new Application(new MacFactory());
        String macResult = macApp.renderUI();
        assertTrue(macResult.contains("Mac Button"), "应包含 Mac Button");
        assertTrue(macResult.contains("Mac TextField"), "应包含 Mac TextField");
    }

    // ==================== 附加题：扩展新产品族 ====================
    
    /**
     * 【附加题】扩展 Linux 产品族
     * 
     * 要求：
     * 1. 实现 LinuxButton 和 LinuxTextField
     * 2. 实现 LinuxFactory
     * 
     * 思考：抽象工厂如何实现开闭原则？新增产品族容易吗？新增产品类型呢？
     */
    
    // TODO: 实现 LinuxButton
    public static class LinuxButton implements Button {
        @Override
        public String render() {
            return null;  // TODO: "Rendering Linux Button"
        }
        
        @Override
        public String click() {
            return null;  // TODO: "Linux Button clicked"
        }
    }
    
    // TODO: 实现 LinuxTextField
    public static class LinuxTextField implements TextField {
        @Override
        public String render() {
            return null;  // TODO: "Rendering Linux TextField"
        }
        
        @Override
        public String input(String text) {
            return null;  // TODO: "Linux TextField input: " + text
        }
    }
    
    // TODO: 实现 LinuxFactory
    public static class LinuxFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return null;  // TODO
        }
        
        @Override
        public TextField createTextField() {
            return null;  // TODO
        }
    }

    @Test
    @DisplayName("附加题：验证扩展产品族")
    void testLinuxFactory() {
        GUIFactory linuxFactory = new LinuxFactory();
        Button linuxButton = linuxFactory.createButton();
        TextField linuxTextField = linuxFactory.createTextField();
        
        assertNotNull(linuxButton, "应创建 LinuxButton");
        assertNotNull(linuxTextField, "应创建 LinuxTextField");
        assertEquals("Rendering Linux Button", linuxButton.render());
        assertEquals("Linux Button clicked", linuxButton.click());
    }
}
