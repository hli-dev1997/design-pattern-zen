package com.hli.design.zen.structural.proxy;

import com.hli.design.zen.structural.proxy.common.SmsService;
import com.hli.design.zen.structural.proxy.v2_static.SmsProxy;
import com.hli.design.zen.structural.proxy.v3_dynamic_jdk.JdkProxyFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author hli
 * @description: 代理模式统一调用测试
 */
@DisplayName("代理模式演进测试")
public class ProxyTest {

    @Test
    @DisplayName("v1 - 烂代码：业务逻辑与计费逻辑耦合严重")
    public void test_v1_bad() {
        System.out.println("--- [v1_bad] ---");
        SmsService service = new com.hli.design.zen.structural.proxy.v1_bad.SmsServiceImpl();
        service.send("欢迎注册 GitHub");
    }

    @Test
    @DisplayName("v2 - 静态代理：通过代理类实现增强，业务逻辑保持纯粹")
    public void test_v2_static() {
        System.out.println("\n--- [v2_static] ---");
        // 核心业务
        SmsService realService = new com.hli.design.zen.structural.proxy.v2_static.SmsServiceImpl();
        // 代理增强
        SmsService proxy = new SmsProxy(realService);
        proxy.send("您的验证码是 123456");
    }

    @Test
    @DisplayName("v3 - JDK 动态代理：通用性更强，可以在运行时为任何接口生成代理")
    public void test_v3_dynamic_jdk() {
        System.out.println("\n--- [v3_dynamic_jdk] ---");
        // 核心任务：可以代理 v2 的 SmsServiceImpl，因为它们都实现了 SmsService 接口
        SmsService realService = new com.hli.design.zen.structural.proxy.v2_static.SmsServiceImpl();

        // 获取代理对象
        SmsService proxy = (SmsService) JdkProxyFactory.getProxy(realService);

        proxy.send("动态代理发送成功");
    }
}
