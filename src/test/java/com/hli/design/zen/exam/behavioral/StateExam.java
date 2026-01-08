package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【状态模式考卷】
 * 场景：实现订单状态机
 */
@DisplayName("状态模式考卷")
public class StateExam {

    // ========== 题目1：状态接口 ==========
    
    public interface OrderState {
        void pay(OrderContext context);
        void ship(OrderContext context);
        void confirm(OrderContext context);
        String getStateName();
    }

    // ========== 题目2：订单上下文 ==========
    
    public static class OrderContext {
        private OrderState state;
        
        public OrderContext() {
            // TODO: 初始状态为 NewState
        }
        
        public void setState(OrderState state) {
            this.state = state;
        }
        
        public void pay() { state.pay(this); }
        public void ship() { state.ship(this); }
        public void confirm() { state.confirm(this); }
        public String getCurrentState() { return state.getStateName(); }
    }

    // ========== 题目3：具体状态 ==========
    
    public static class NewState implements OrderState {
        @Override
        public void pay(OrderContext context) {
            // TODO: 切换到 PaidState
        }
        
        @Override
        public void ship(OrderContext context) {
            // 不允许：未付款不能发货
        }
        
        @Override
        public void confirm(OrderContext context) {
            // 不允许
        }
        
        @Override
        public String getStateName() { return "NEW"; }
    }
    
    public static class PaidState implements OrderState {
        @Override
        public void pay(OrderContext context) {
            // 已付款，忽略
        }
        
        @Override
        public void ship(OrderContext context) {
            // TODO: 切换到 ShippedState
        }
        
        @Override
        public void confirm(OrderContext context) {
            // 不允许
        }
        
        @Override
        public String getStateName() { return "PAID"; }
    }
    
    public static class ShippedState implements OrderState {
        @Override
        public void pay(OrderContext context) { }
        
        @Override
        public void ship(OrderContext context) { }
        
        @Override
        public void confirm(OrderContext context) {
            // TODO: 切换到 CompletedState
        }
        
        @Override
        public String getStateName() { return "SHIPPED"; }
    }
    
    public static class CompletedState implements OrderState {
        @Override
        public void pay(OrderContext context) { }
        @Override
        public void ship(OrderContext context) { }
        @Override
        public void confirm(OrderContext context) { }
        @Override
        public String getStateName() { return "COMPLETED"; }
    }

    @Test
    @DisplayName("验证状态流转")
    void testState() {
        OrderContext order = new OrderContext();
        assertEquals("NEW", order.getCurrentState());
        
        order.ship(); // 无效操作
        assertEquals("NEW", order.getCurrentState());
        
        order.pay();
        assertEquals("PAID", order.getCurrentState());
        
        order.ship();
        assertEquals("SHIPPED", order.getCurrentState());
        
        order.confirm();
        assertEquals("COMPLETED", order.getCurrentState());
    }
}
