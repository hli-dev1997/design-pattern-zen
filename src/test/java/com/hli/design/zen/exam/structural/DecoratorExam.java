package com.hli.design.zen.exam.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【装饰器模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共3道题，满分100分
 * 2. 场景：实现咖啡订单系统的配料装饰
 *
 * 验收标准：
 * - 题目1：定义组件接口和具体组件
 * - 题目2：实现装饰器抽象类
 * - 题目3：实现多个具体装饰器并组合使用
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("装饰器模式考卷")
public class DecoratorExam {

    // ==================== 题目1：组件定义 ====================
    
    /**
     * 【题目1-1】饮料接口（抽象组件）
     */
    public interface Beverage {
        /**
         * 获取饮料描述
         */
        String getDescription();
        
        /**
         * 获取价格
         */
        double cost();
    }
    
    /**
     * 【题目1-2】浓缩咖啡（具体组件）
     * TODO: 实现 Beverage 接口
     */
    public static class Espresso implements Beverage {
        @Override
        public String getDescription() {
            // TODO: 返回 "Espresso"
            return null;
        }
        
        @Override
        public double cost() {
            // TODO: 返回 1.99
            return 0;
        }
    }
    
    /**
     * 【题目1-3】深焙咖啡（具体组件）
     * TODO: 实现 Beverage 接口
     */
    public static class DarkRoast implements Beverage {
        @Override
        public String getDescription() {
            // TODO: 返回 "Dark Roast Coffee"
            return null;
        }
        
        @Override
        public double cost() {
            // TODO: 返回 0.99
            return 0;
        }
    }

    @Test
    @DisplayName("题目1：验证具体组件")
    void testConcreteComponents() {
        Beverage espresso = new Espresso();
        assertEquals("Espresso", espresso.getDescription());
        assertEquals(1.99, espresso.cost(), 0.01);
        
        Beverage darkRoast = new DarkRoast();
        assertEquals("Dark Roast Coffee", darkRoast.getDescription());
        assertEquals(0.99, darkRoast.cost(), 0.01);
    }

    // ==================== 题目2：装饰器抽象类 ====================
    
    /**
     * 【题目2】调料装饰器（抽象装饰器）
     * 
     * 要求：
     * 1. 实现 Beverage 接口
     * 2. 持有一个 Beverage 引用（被装饰对象）
     * 3. getDescription() 声明为抽象方法
     */
    public static abstract class CondimentDecorator implements Beverage {
        // TODO: 声明被装饰的饮料
        protected Beverage beverage;
        
        public CondimentDecorator(Beverage beverage) {
            // TODO: 保存被装饰对象
        }
        
        // getDescription() 保持抽象，由子类实现
        @Override
        public abstract String getDescription();
    }

    // ==================== 题目3：具体装饰器 ====================
    
    /**
     * 【题目3-1】牛奶装饰器
     * TODO: 继承 CondimentDecorator
     */
    public static class Milk extends CondimentDecorator {
        
        public Milk(Beverage beverage) {
            super(beverage);
        }
        
        @Override
        public String getDescription() {
            // TODO: 返回 被装饰对象描述 + ", Milk"
            return null;
        }
        
        @Override
        public double cost() {
            // TODO: 返回 被装饰对象价格 + 0.30
            return 0;
        }
    }
    
    /**
     * 【题目3-2】摩卡装饰器
     * TODO: 继承 CondimentDecorator
     */
    public static class Mocha extends CondimentDecorator {
        
        public Mocha(Beverage beverage) {
            super(beverage);
        }
        
        @Override
        public String getDescription() {
            // TODO: 返回 被装饰对象描述 + ", Mocha"
            return null;
        }
        
        @Override
        public double cost() {
            // TODO: 返回 被装饰对象价格 + 0.20
            return 0;
        }
    }
    
    /**
     * 【题目3-3】奶泡装饰器
     * TODO: 继承 CondimentDecorator
     */
    public static class Whip extends CondimentDecorator {
        
        public Whip(Beverage beverage) {
            super(beverage);
        }
        
        @Override
        public String getDescription() {
            // TODO: 返回 被装饰对象描述 + ", Whip"
            return null;
        }
        
        @Override
        public double cost() {
            // TODO: 返回 被装饰对象价格 + 0.10
            return 0;
        }
    }

    @Test
    @DisplayName("题目3：验证装饰器组合")
    void testDecoratorCombination() {
        // 单个装饰器
        Beverage espressoWithMilk = new Milk(new Espresso());
        assertEquals("Espresso, Milk", espressoWithMilk.getDescription());
        assertEquals(2.29, espressoWithMilk.cost(), 0.01);
        
        // 多个装饰器叠加
        Beverage darkRoastFull = new Whip(new Mocha(new Mocha(new DarkRoast())));
        assertEquals("Dark Roast Coffee, Mocha, Mocha, Whip", darkRoastFull.getDescription());
        // 0.99 + 0.20 + 0.20 + 0.10 = 1.49
        assertEquals(1.49, darkRoastFull.cost(), 0.01);
    }

    @Test
    @DisplayName("综合测试：完整订单")
    void testCompleteOrder() {
        // 大杯摩卡星冰乐：Espresso + 双份Mocha + Milk + Whip
        Beverage order = new Whip(new Milk(new Mocha(new Mocha(new Espresso()))));
        
        String description = order.getDescription();
        assertTrue(description.contains("Espresso"), "应包含Espresso");
        assertTrue(description.contains("Mocha"), "应包含Mocha");
        assertTrue(description.contains("Milk"), "应包含Milk");
        assertTrue(description.contains("Whip"), "应包含Whip");
        
        // 1.99 + 0.20 + 0.20 + 0.30 + 0.10 = 2.79
        assertEquals(2.79, order.cost(), 0.01);
    }

    // ==================== 附加题：带数量的装饰器 ====================
    
    /**
     * 【附加题】实现可指定数量的装饰器
     * 
     * 要求：糖浆装饰器可以指定份数，每份 0.15
     */
    public static class Syrup extends CondimentDecorator {
        private int pumps;  // 份数
        
        public Syrup(Beverage beverage, int pumps) {
            super(beverage);
            // TODO: 保存份数
        }
        
        @Override
        public String getDescription() {
            // TODO: 返回格式 "xxx, Syrup x N"（N为份数）
            return null;
        }
        
        @Override
        public double cost() {
            // TODO: 返回 被装饰对象价格 + (0.15 * pumps)
            return 0;
        }
    }

    @Test
    @DisplayName("附加题：验证带数量的装饰器")
    void testSyrupDecorator() {
        Beverage sweetCoffee = new Syrup(new DarkRoast(), 3);
        
        assertTrue(sweetCoffee.getDescription().contains("Syrup"), "应包含Syrup");
        assertTrue(sweetCoffee.getDescription().contains("3"), "应包含份数3");
        
        // 0.99 + 0.15 * 3 = 1.44
        assertEquals(1.44, sweetCoffee.cost(), 0.01);
    }
}
