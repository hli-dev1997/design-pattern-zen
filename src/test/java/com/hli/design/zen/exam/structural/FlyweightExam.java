package com.hli.design.zen.exam.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【享元模式考卷】
 * 场景：实现围棋棋子的共享
 */
@DisplayName("享元模式考卷")
public class FlyweightExam {

    // ========== 题目1：享元接口和具体享元 ==========
    
    public interface ChessPiece {
        String getColor();
        void display(int x, int y);
    }
    
    public static class ConcreteChessPiece implements ChessPiece {
        private String color; // 内部状态
        
        public ConcreteChessPiece(String color) {
            // TODO: 保存颜色
        }
        
        @Override
        public String getColor() {
            return null; // TODO
        }
        
        @Override
        public void display(int x, int y) {
            // TODO: 打印 "color piece at (x, y)"
        }
    }

    // ========== 题目2：享元工厂 ==========
    
    public static class ChessPieceFactory {
        private Map<String, ChessPiece> pieces = new HashMap<>();
        
        public ChessPiece getChessPiece(String color) {
            // TODO: 如果已存在则返回缓存，否则创建新的并缓存
            return null;
        }
        
        public int getPoolSize() {
            return pieces.size();
        }
    }

    @Test
    @DisplayName("验证享元共享")
    void testFlyweight() {
        ChessPieceFactory factory = new ChessPieceFactory();
        
        ChessPiece black1 = factory.getChessPiece("Black");
        ChessPiece black2 = factory.getChessPiece("Black");
        ChessPiece white1 = factory.getChessPiece("White");
        
        assertSame(black1, black2, "相同颜色应返回同一对象");
        assertNotSame(black1, white1, "不同颜色应是不同对象");
        assertEquals(2, factory.getPoolSize(), "池中应只有2个对象");
    }
}
