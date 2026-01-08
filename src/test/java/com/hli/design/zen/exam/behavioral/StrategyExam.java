package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【策略模式考卷】
 * 场景：实现支付方式的策略切换
 */
@DisplayName("策略模式考卷")
public class StrategyExam {

    // ========== 题目1：策略接口 ==========
    
    public interface PaymentStrategy {
        String pay(BigDecimal amount);
    }
    
    public static class AlipayStrategy implements PaymentStrategy {
        @Override
        public String pay(BigDecimal amount) {
            // TODO: 返回 "Paid " + amount + " via Alipay"
            return null;
        }
    }
    
    public static class WechatPayStrategy implements PaymentStrategy {
        @Override
        public String pay(BigDecimal amount) {
            // TODO: 返回 "Paid " + amount + " via WechatPay"
            return null;
        }
    }
    
    public static class BankCardStrategy implements PaymentStrategy {
        @Override
        public String pay(BigDecimal amount) {
            // TODO: 返回 "Paid " + amount + " via BankCard"
            return null;
        }
    }

    // ========== 题目2：策略上下文 ==========
    
    public static class PaymentContext {
        private PaymentStrategy strategy;
        
        public void setStrategy(PaymentStrategy strategy) {
            // TODO
        }
        
        public String executePayment(BigDecimal amount) {
            // TODO: 调用策略执行支付
            return null;
        }
    }

    @Test
    @DisplayName("验证策略切换")
    void testStrategy() {
        PaymentContext context = new PaymentContext();
        BigDecimal amount = new BigDecimal("100.00");
        
        context.setStrategy(new AlipayStrategy());
        assertTrue(context.executePayment(amount).contains("Alipay"));
        
        context.setStrategy(new WechatPayStrategy());
        assertTrue(context.executePayment(amount).contains("WechatPay"));
    }

    // ========== 附加题：策略注册表 ==========
    
    public static class StrategyRegistry {
        private Map<String, PaymentStrategy> strategies = new HashMap<>();
        
        public void register(String name, PaymentStrategy strategy) {
            // TODO
        }
        
        public PaymentStrategy getStrategy(String name) {
            // TODO
            return null;
        }
    }

    @Test
    @DisplayName("附加题：策略注册表")
    void testRegistry() {
        StrategyRegistry registry = new StrategyRegistry();
        registry.register("alipay", new AlipayStrategy());
        registry.register("wechat", new WechatPayStrategy());
        
        PaymentStrategy alipay = registry.getStrategy("alipay");
        assertNotNull(alipay);
    }
}
