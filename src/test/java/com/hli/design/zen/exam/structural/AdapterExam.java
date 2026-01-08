package com.hli.design.zen.exam.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【适配器模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共3道题，满分100分
 * 2. 场景：将第三方支付SDK适配到统一支付接口
 *
 * 验收标准：
 * - 题目1：实现类适配器（通过继承）
 * - 题目2：实现对象适配器（通过组合）
 * - 题目3：实现双向适配器
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("适配器模式考卷")
public class AdapterExam {

    // ==================== 目标接口（我们的系统接口）====================
    
    /**
     * 统一支付接口（Target）
     */
    public interface PaymentGateway {
        /**
         * 支付
         * @param amount 金额
         * @return 支付结果
         */
        String pay(double amount);
        
        /**
         * 退款
         * @param transactionId 交易ID
         * @return 退款结果
         */
        String refund(String transactionId);
    }

    // ==================== 第三方SDK（需要适配的类）====================
    
    /**
     * 支付宝SDK（Adaptee）
     * 注意：方法签名与 PaymentGateway 不同
     */
    public static class AlipaySDK {
        public String sendPayment(double money, String currency) {
            return "Alipay: paid " + money + " " + currency;
        }
        
        public String processRefund(String orderId, String reason) {
            return "Alipay: refunded " + orderId;
        }
    }
    
    /**
     * 微信支付SDK（Adaptee）
     * 注意：方法签名与 PaymentGateway 不同
     */
    public static class WechatPaySDK {
        public String createPayment(int amountInCents) {
            return "WechatPay: paid " + amountInCents + " cents";
        }
        
        public String cancelPayment(String wxOrderId) {
            return "WechatPay: cancelled " + wxOrderId;
        }
    }

    // ==================== 题目1：类适配器 ====================
    
    /**
     * 【题目1】支付宝类适配器
     * 
     * 要求：
     * 1. 继承 AlipaySDK
     * 2. 实现 PaymentGateway 接口
     * 3. 适配方法调用
     */
    public static class AlipayClassAdapter extends AlipaySDK implements PaymentGateway {
        
        @Override
        public String pay(double amount) {
            // TODO: 调用父类的 sendPayment 方法
            // 提示：货币默认使用 "CNY"
            return null;
        }
        
        @Override
        public String refund(String transactionId) {
            // TODO: 调用父类的 processRefund 方法
            // 提示：reason 默认使用 "User requested"
            return null;
        }
    }

    @Test
    @DisplayName("题目1：验证类适配器")
    void testClassAdapter() {
        PaymentGateway alipay = new AlipayClassAdapter();
        
        String payResult = alipay.pay(100.0);
        assertNotNull(payResult);
        assertTrue(payResult.contains("Alipay"), "应包含Alipay标识");
        assertTrue(payResult.contains("100"), "应包含金额");
        
        String refundResult = alipay.refund("TXN001");
        assertNotNull(refundResult);
        assertTrue(refundResult.contains("refund"), "应包含refund");
    }

    // ==================== 题目2：对象适配器 ====================
    
    /**
     * 【题目2】微信支付对象适配器
     * 
     * 要求：
     * 1. 通过组合持有 WechatPaySDK 实例
     * 2. 实现 PaymentGateway 接口
     * 3. 金额转换：元转分（乘以100）
     */
    public static class WechatPayObjectAdapter implements PaymentGateway {
        private WechatPaySDK wechatPaySDK;
        
        public WechatPayObjectAdapter(WechatPaySDK wechatPaySDK) {
            // TODO: 保存 SDK 实例
        }
        
        @Override
        public String pay(double amount) {
            // TODO: 
            // 1. 将元转换为分（amount * 100）
            // 2. 调用 wechatPaySDK.createPayment()
            return null;
        }
        
        @Override
        public String refund(String transactionId) {
            // TODO: 调用 wechatPaySDK.cancelPayment()
            return null;
        }
    }

    @Test
    @DisplayName("题目2：验证对象适配器")
    void testObjectAdapter() {
        WechatPaySDK sdk = new WechatPaySDK();
        PaymentGateway wechatPay = new WechatPayObjectAdapter(sdk);
        
        String payResult = wechatPay.pay(10.5);  // 10.5元
        assertNotNull(payResult);
        assertTrue(payResult.contains("WechatPay"), "应包含WechatPay标识");
        assertTrue(payResult.contains("1050"), "应转换为1050分");
        
        String refundResult = wechatPay.refund("WX001");
        assertNotNull(refundResult);
        assertTrue(refundResult.contains("cancelled"), "应包含cancelled");
    }

    // ==================== 题目3：双向适配器 ====================
    
    /**
     * 旧系统接口（Legacy）
     */
    public interface LegacyPayment {
        String oldPay(String amountStr);
    }
    
    /**
     * 新系统接口（Modern）
     */
    public interface ModernPayment {
        String newPay(double amount);
    }
    
    /**
     * 【题目3】双向适配器
     * 
     * 要求：
     * 1. 同时实现 LegacyPayment 和 ModernPayment 接口
     * 2. 内部可以使用任意一个实际实现
     * 3. 实现双向转换
     */
    public static class TwoWayAdapter implements LegacyPayment, ModernPayment {
        private LegacyPayment legacyPayment;
        private ModernPayment modernPayment;
        
        // 从旧系统适配
        public TwoWayAdapter(LegacyPayment legacyPayment) {
            this.legacyPayment = legacyPayment;
        }
        
        // 从新系统适配
        public TwoWayAdapter(ModernPayment modernPayment) {
            this.modernPayment = modernPayment;
        }
        
        @Override
        public String oldPay(String amountStr) {
            // TODO:
            // 如果有 legacyPayment，直接调用
            // 否则将 amountStr 转为 double，调用 modernPayment.newPay()
            return null;
        }
        
        @Override
        public String newPay(double amount) {
            // TODO:
            // 如果有 modernPayment，直接调用
            // 否则将 amount 转为 String，调用 legacyPayment.oldPay()
            return null;
        }
    }
    
    // 旧系统实现
    public static class LegacyPaymentImpl implements LegacyPayment {
        @Override
        public String oldPay(String amountStr) {
            return "Legacy paid: " + amountStr;
        }
    }
    
    // 新系统实现
    public static class ModernPaymentImpl implements ModernPayment {
        @Override
        public String newPay(double amount) {
            return "Modern paid: " + amount;
        }
    }

    @Test
    @DisplayName("题目3：验证双向适配器")
    void testTwoWayAdapter() {
        // 旧系统适配到新接口
        LegacyPayment legacy = new LegacyPaymentImpl();
        TwoWayAdapter adapter1 = new TwoWayAdapter(legacy);
        
        String result1 = adapter1.newPay(100.0);
        assertNotNull(result1);
        assertTrue(result1.contains("100"), "应包含金额");
        
        // 新系统适配到旧接口
        ModernPayment modern = new ModernPaymentImpl();
        TwoWayAdapter adapter2 = new TwoWayAdapter(modern);
        
        String result2 = adapter2.oldPay("200");
        assertNotNull(result2);
        assertTrue(result2.contains("200"), "应包含金额");
    }

    // ==================== 附加题：默认适配器 ====================
    
    /**
     * 【附加题】实现默认适配器
     * 
     * 场景：有一个大接口，但我们只需要实现部分方法
     */
    public interface FullFeaturePayment {
        void pay();
        void refund();
        void query();
        void cancel();
        void sendNotification();  // 避免与Object.notify()冲突
    }
    
    /**
     * 默认适配器：提供空实现
     */
    public static abstract class DefaultPaymentAdapter implements FullFeaturePayment {
        // TODO: 为所有方法提供空的默认实现
        @Override
        public void pay() {}
        
        @Override
        public void refund() {}
        
        @Override
        public void query() {}
        
        @Override
        public void cancel() {}
        
        @Override
        public void sendNotification() {}
    }
    
    /**
     * 只实现需要的方法
     */
    public static class SimplePayment extends DefaultPaymentAdapter {
        private boolean paid = false;
        
        @Override
        public void pay() {
            // TODO: 只实现 pay 方法
            paid = true;
        }
        
        public boolean isPaid() {
            return paid;
        }
    }

    @Test
    @DisplayName("附加题：验证默认适配器")
    void testDefaultAdapter() {
        SimplePayment payment = new SimplePayment();
        
        assertFalse(payment.isPaid());
        payment.pay();
        assertTrue(payment.isPaid());
        
        // 其他方法可以调用但没有效果
        payment.refund();
        payment.query();
        // 不会抛出异常
    }
}
