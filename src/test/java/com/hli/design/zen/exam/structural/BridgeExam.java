package com.hli.design.zen.exam.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【桥接模式考卷】
 * 场景：实现跨平台图形绘制（形状 × 颜色）
 */
@DisplayName("桥接模式考卷")
public class BridgeExam {

    // ========== 题目1：实现层 ==========
    
    public interface Color {
        String fill();
    }
    
    public static class RedColor implements Color {
        @Override
        public String fill() {
            return null; // TODO: 返回 "Red"
        }
    }
    
    public static class BlueColor implements Color {
        @Override
        public String fill() {
            return null; // TODO: 返回 "Blue"
        }
    }

    @Test
    @DisplayName("题目1：验证颜色实现")
    void testColors() {
        assertEquals("Red", new RedColor().fill());
        assertEquals("Blue", new BlueColor().fill());
    }

    // ========== 题目2：抽象层与桥接 ==========
    
    public static abstract class Shape {
        protected Color color;
        
        public Shape(Color color) {
            // TODO: 保存颜色
        }
        
        public abstract String draw();
    }
    
    public static class Circle extends Shape {
        public Circle(Color color) { super(color); }
        
        @Override
        public String draw() {
            // TODO: 返回 "Drawing Circle with " + color.fill() + " color"
            return null;
        }
    }
    
    public static class Rectangle extends Shape {
        public Rectangle(Color color) { super(color); }
        
        @Override
        public String draw() {
            // TODO: 返回 "Drawing Rectangle with " + color.fill() + " color"
            return null;
        }
    }

    @Test
    @DisplayName("题目2：验证桥接组合")
    void testBridge() {
        Shape redCircle = new Circle(new RedColor());
        assertEquals("Drawing Circle with Red color", redCircle.draw());
        
        Shape blueRect = new Rectangle(new BlueColor());
        assertEquals("Drawing Rectangle with Blue color", blueRect.draw());
    }
}
