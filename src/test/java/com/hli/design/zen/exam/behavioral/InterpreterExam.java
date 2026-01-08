package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【解释器模式考卷】
 * 场景：实现简单的数学表达式解释器
 */
@DisplayName("解释器模式考卷")
public class InterpreterExam {

    // ========== 题目1：表达式接口 ==========
    
    public interface Expression {
        int interpret();
    }

    // ========== 题目2：终结符表达式 ==========
    
    public static class NumberExpression implements Expression {
        private int number;
        
        public NumberExpression(int number) {
            this.number = number;
        }
        
        @Override
        public int interpret() {
            // TODO: 返回数字
            return 0;
        }
    }

    // ========== 题目3：非终结符表达式 ==========
    
    public static class AddExpression implements Expression {
        private Expression left;
        private Expression right;
        
        public AddExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int interpret() {
            // TODO: 返回 left + right
            return 0;
        }
    }
    
    public static class SubtractExpression implements Expression {
        private Expression left;
        private Expression right;
        
        public SubtractExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int interpret() {
            // TODO: 返回 left - right
            return 0;
        }
    }
    
    public static class MultiplyExpression implements Expression {
        private Expression left;
        private Expression right;
        
        public MultiplyExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int interpret() {
            // TODO: 返回 left * right
            return 0;
        }
    }

    @Test
    @DisplayName("验证解释器")
    void testInterpreter() {
        // 表达式: (5 + 3) * 2 - 4 = 12
        Expression five = new NumberExpression(5);
        Expression three = new NumberExpression(3);
        Expression two = new NumberExpression(2);
        Expression four = new NumberExpression(4);
        
        Expression add = new AddExpression(five, three);      // 5 + 3 = 8
        Expression multiply = new MultiplyExpression(add, two); // 8 * 2 = 16
        Expression result = new SubtractExpression(multiply, four); // 16 - 4 = 12
        
        assertEquals(12, result.interpret());
    }
}
