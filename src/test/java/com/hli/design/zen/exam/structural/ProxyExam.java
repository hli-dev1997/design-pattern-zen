package com.hli.design.zen.exam.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【代理模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共4道题，满分100分
 * 2. 场景：实现用户服务的访问控制代理
 *
 * 验收标准：
 * - 题目1：实现静态代理
 * - 题目2：实现JDK动态代理
 * - 题目3：实现CGLIB风格代理（模拟）
 * - 题目4：实现缓存代理
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("代理模式考卷")
public class ProxyExam {

    // ==================== 通用接口 ====================
    
    public interface UserService {
        String findUser(Long id);
        void saveUser(String name);
    }
    
    /**
     * 真实服务实现
     */
    public static class UserServiceImpl implements UserService {
        @Override
        public String findUser(Long id) {
            return "User-" + id;
        }
        
        @Override
        public void saveUser(String name) {
            System.out.println("Saving user: " + name);
        }
    }

    // ==================== 题目1：静态代理 ====================
    
    /**
     * 【题目1】实现静态代理
     * 
     * 要求：
     * 1. 实现 UserService 接口
     * 2. 在调用真实方法前后添加日志
     * 3. 记录方法调用次数
     */
    public static class UserServiceProxy implements UserService {
        private UserService target;
        private int callCount = 0;
        
        public UserServiceProxy(UserService target) {
            // TODO: 保存目标对象
        }
        
        @Override
        public String findUser(Long id) {
            // TODO: 
            // 1. 增加调用计数
            // 2. 打印日志 "Before findUser"
            // 3. 调用目标方法
            // 4. 打印日志 "After findUser"
            // 5. 返回结果
            return null;
        }
        
        @Override
        public void saveUser(String name) {
            // TODO: 同上，添加前后日志
        }
        
        public int getCallCount() {
            return callCount;
        }
    }

    @Test
    @DisplayName("题目1：验证静态代理")
    void testStaticProxy() {
        UserService target = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy(target);
        
        String result = proxy.findUser(1L);
        assertEquals("User-1", result, "应返回正确的用户");
        assertEquals(1, proxy.getCallCount(), "调用次数应为1");
        
        proxy.findUser(2L);
        assertEquals(2, proxy.getCallCount(), "调用次数应为2");
    }

    // ==================== 题目2：JDK动态代理 ====================
    
    /**
     * 【题目2】实现JDK动态代理
     * 
     * 要求：
     * 1. 使用 java.lang.reflect.Proxy 创建代理
     * 2. 在 InvocationHandler 中实现日志功能
     * 3. 方法调用前打印方法名，调用后打印返回值
     */
    public static class JdkProxyFactory {
        
        /**
         * 创建代理对象
         * @param target 目标对象
         * @return 代理对象
         */
        @SuppressWarnings("unchecked")
        public static <T> T createProxy(T target) {
            // TODO: 使用 Proxy.newProxyInstance 创建代理
            // 提示：
            // 1. 获取目标类的 ClassLoader
            // 2. 获取目标类实现的接口
            // 3. 创建 InvocationHandler
            return null;
        }
    }
    
    /**
     * JDK动态代理的 InvocationHandler
     * TODO: 实现 java.lang.reflect.InvocationHandler
     */
    public static class LoggingInvocationHandler implements java.lang.reflect.InvocationHandler {
        private Object target;
        private StringBuilder log = new StringBuilder();
        
        public LoggingInvocationHandler(Object target) {
            this.target = target;
        }
        
        @Override
        public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable {
            // TODO:
            // 1. 记录 "Calling: 方法名"
            // 2. 调用目标方法
            // 3. 记录 "Result: 返回值"
            // 4. 返回结果
            return null;
        }
        
        public String getLog() {
            return log.toString();
        }
    }

    @Test
    @DisplayName("题目2：验证JDK动态代理")
    void testJdkDynamicProxy() {
        UserService target = new UserServiceImpl();
        UserService proxy = JdkProxyFactory.createProxy(target);
        
        assertNotNull(proxy, "代理对象不应为null");
        
        String result = proxy.findUser(100L);
        assertEquals("User-100", result, "应返回正确结果");
        
        // 验证代理类型
        assertTrue(java.lang.reflect.Proxy.isProxyClass(proxy.getClass()), 
            "应该是JDK动态代理类");
    }

    // ==================== 题目3：模拟CGLIB代理 ====================
    
    /**
     * 【题目3】理解CGLIB代理原理
     * 
     * 说明：CGLIB通过继承目标类实现代理
     * 要求：模拟CGLIB的继承代理方式
     */
    public static class RealService {
        public String process(String data) {
            return "Processed: " + data;
        }
    }
    
    /**
     * 模拟CGLIB代理：通过继承实现
     * TODO: 继承 RealService，重写 process 方法
     */
    public static class CglibStyleProxy extends RealService {
        private boolean preCheck = true;
        
        @Override
        public String process(String data) {
            // TODO:
            // 1. 前置检查（如果 preCheck 为 false，抛出异常）
            // 2. 调用父类方法
            // 3. 返回结果
            return null;
        }
        
        public void setPreCheck(boolean preCheck) {
            this.preCheck = preCheck;
        }
    }

    @Test
    @DisplayName("题目3：验证CGLIB风格代理")
    void testCglibStyleProxy() {
        CglibStyleProxy proxy = new CglibStyleProxy();
        
        // 正常调用
        String result = proxy.process("test");
        assertEquals("Processed: test", result);
        
        // 前置检查失败
        proxy.setPreCheck(false);
        assertThrows(RuntimeException.class, () -> proxy.process("test"),
            "前置检查失败应抛出异常");
    }

    // ==================== 题目4：缓存代理 ====================
    
    /**
     * 【题目4】实现缓存代理
     * 
     * 场景：为查询方法添加缓存，相同参数只查询一次
     */
    public interface DataService {
        String queryData(String key);
    }
    
    public static class SlowDataService implements DataService {
        private int queryCount = 0;
        
        @Override
        public String queryData(String key) {
            queryCount++;
            // 模拟慢查询
            return "Data-" + key;
        }
        
        public int getQueryCount() {
            return queryCount;
        }
    }
    
    /**
     * 缓存代理
     * TODO: 实现缓存逻辑
     */
    public static class CachingProxy implements DataService {
        private DataService target;
        private java.util.Map<String, String> cache = new java.util.HashMap<>();
        
        public CachingProxy(DataService target) {
            this.target = target;
        }
        
        @Override
        public String queryData(String key) {
            // TODO:
            // 1. 检查缓存是否存在
            // 2. 存在则直接返回缓存
            // 3. 不存在则调用目标方法，结果存入缓存后返回
            return null;
        }
        
        public int getCacheSize() {
            return cache.size();
        }
    }

    @Test
    @DisplayName("题目4：验证缓存代理")
    void testCachingProxy() {
        SlowDataService target = new SlowDataService();
        CachingProxy proxy = new CachingProxy(target);
        
        // 第一次查询
        String result1 = proxy.queryData("key1");
        assertEquals("Data-key1", result1);
        assertEquals(1, target.getQueryCount(), "应该调用了目标方法");
        
        // 第二次查询相同key，应该走缓存
        String result2 = proxy.queryData("key1");
        assertEquals("Data-key1", result2);
        assertEquals(1, target.getQueryCount(), "应该走缓存，不再调用目标方法");
        
        // 查询不同key
        proxy.queryData("key2");
        assertEquals(2, target.getQueryCount(), "新key应该调用目标方法");
        assertEquals(2, proxy.getCacheSize(), "缓存应该有2条记录");
    }
}
