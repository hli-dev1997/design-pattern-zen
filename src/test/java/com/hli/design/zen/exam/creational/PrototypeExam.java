package com.hli.design.zen.exam.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【原型模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共3道题，满分100分
 * 2. 场景：实现对象克隆，理解深拷贝与浅拷贝
 *
 * 验收标准：
 * - 题目1：实现 Cloneable 接口（浅拷贝）
 * - 题目2：实现深拷贝
 * - 题目3：实现原型注册表
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("原型模式考卷")
public class PrototypeExam {

    // ==================== 题目1：浅拷贝 ====================
    
    /**
     * 【题目1】实现浅拷贝
     * 
     * 要求：
     * 1. 实现 Cloneable 接口
     * 2. 重写 clone() 方法
     * 3. 注意：浅拷贝不会复制引用类型的内部对象
     */
    public static class ShallowCopyPrototype implements Cloneable {
        private String name;
        private int[] scores;  // 引用类型
        
        public ShallowCopyPrototype(String name, int[] scores) {
            this.name = name;
            this.scores = scores;
        }
        
        @Override
        public ShallowCopyPrototype clone() {
            // TODO: 实现浅拷贝
            // 提示：调用 super.clone()
            return null;  // TODO
        }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int[] getScores() { return scores; }
        public void setScores(int[] scores) { this.scores = scores; }
    }

    @Test
    @DisplayName("题目1：验证浅拷贝特性")
    void testShallowCopy() {
        int[] scores = {90, 85, 88};
        ShallowCopyPrototype original = new ShallowCopyPrototype("Alice", scores);
        ShallowCopyPrototype cloned = original.clone();
        
        // 验证是不同对象
        assertNotSame(original, cloned, "克隆应创建新对象");
        
        // 验证基本属性相同
        assertEquals(original.getName(), cloned.getName(), "名字应相同");
        
        // 验证浅拷贝特性：引用类型指向同一对象
        assertSame(original.getScores(), cloned.getScores(), 
            "浅拷贝：数组应该是同一个引用");
        
        // 修改原对象的数组，克隆对象也会受影响
        original.getScores()[0] = 100;
        assertEquals(100, cloned.getScores()[0], 
            "浅拷贝：修改原对象数组，克隆对象也受影响");
    }

    // ==================== 题目2：深拷贝 ====================
    
    /**
     * 【题目2】实现深拷贝
     * 
     * 要求：
     * 1. 引用类型也要完全复制
     * 2. 修改原对象不影响克隆对象
     */
    public static class DeepCopyPrototype implements Cloneable {
        private String name;
        private int[] scores;
        private Address address;  // 嵌套对象
        
        public DeepCopyPrototype(String name, int[] scores, Address address) {
            this.name = name;
            this.scores = scores;
            this.address = address;
        }
        
        @Override
        public DeepCopyPrototype clone() {
            // TODO: 实现深拷贝
            // 提示：
            // 1. 调用 super.clone() 获取浅拷贝
            // 2. 手动复制数组（Arrays.copyOf）
            // 3. 手动克隆 Address 对象
            return null;  // TODO
        }
        
        // Getter/Setter
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int[] getScores() { return scores; }
        public Address getAddress() { return address; }
    }
    
    /**
     * 地址类（也需要支持克隆）
     */
    public static class Address implements Cloneable {
        private String city;
        private String street;
        
        public Address(String city, String street) {
            this.city = city;
            this.street = street;
        }
        
        @Override
        public Address clone() {
            // TODO: 实现克隆
            return null;  // TODO
        }
        
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
    }

    @Test
    @DisplayName("题目2：验证深拷贝特性")
    void testDeepCopy() {
        int[] scores = {90, 85, 88};
        Address address = new Address("Beijing", "Zhongguancun");
        DeepCopyPrototype original = new DeepCopyPrototype("Bob", scores, address);
        DeepCopyPrototype cloned = original.clone();
        
        // 验证是不同对象
        assertNotSame(original, cloned, "克隆应创建新对象");
        
        // 验证深拷贝：数组是不同对象
        assertNotSame(original.getScores(), cloned.getScores(), 
            "深拷贝：数组应该是不同对象");
        
        // 验证深拷贝：修改原对象不影响克隆对象
        original.getScores()[0] = 100;
        assertEquals(90, cloned.getScores()[0], 
            "深拷贝：修改原对象数组，克隆对象不应受影响");
        
        // 验证深拷贝：嵌套对象也是不同对象
        assertNotSame(original.getAddress(), cloned.getAddress(), 
            "深拷贝：Address 应该是不同对象");
        
        original.getAddress().setCity("Shanghai");
        assertEquals("Beijing", cloned.getAddress().getCity(), 
            "深拷贝：修改原对象地址，克隆对象不应受影响");
    }

    // ==================== 题目3：原型注册表 ====================
    
    /**
     * 【题目3】实现原型注册表
     * 
     * 要求：
     * 1. 可以注册原型对象
     * 2. 可以根据名称获取克隆对象
     * 3. 每次获取都返回新的克隆对象
     */
    public interface Prototype {
        Prototype clone();
        String getType();
    }
    
    public static class Circle implements Prototype {
        private int radius;
        
        public Circle(int radius) {
            this.radius = radius;
        }
        
        @Override
        public Prototype clone() {
            // TODO: 返回新的 Circle 对象
            return null;
        }
        
        @Override
        public String getType() {
            return "Circle";
        }
        
        public int getRadius() { return radius; }
    }
    
    public static class Rectangle implements Prototype {
        private int width;
        private int height;
        
        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public Prototype clone() {
            // TODO: 返回新的 Rectangle 对象
            return null;
        }
        
        @Override
        public String getType() {
            return "Rectangle";
        }
        
        public int getWidth() { return width; }
        public int getHeight() { return height; }
    }
    
    /**
     * 原型注册表
     */
    public static class PrototypeRegistry {
        private java.util.Map<String, Prototype> prototypes = new java.util.HashMap<>();
        
        /**
         * 注册原型
         */
        public void register(String name, Prototype prototype) {
            // TODO: 将原型存入 Map
        }
        
        /**
         * 获取克隆对象
         * @return 原型的克隆，如果不存在返回 null
         */
        public Prototype get(String name) {
            // TODO: 从 Map 获取原型并返回克隆
            // 提示：prototype.clone()
            return null;
        }
    }

    @Test
    @DisplayName("题目3：验证原型注册表")
    void testPrototypeRegistry() {
        PrototypeRegistry registry = new PrototypeRegistry();
        
        // 注册原型
        registry.register("circle", new Circle(10));
        registry.register("rectangle", new Rectangle(20, 30));
        
        // 获取克隆
        Prototype circle1 = registry.get("circle");
        Prototype circle2 = registry.get("circle");
        
        assertNotNull(circle1, "应该能获取到 circle");
        assertNotNull(circle2, "应该能获取到 circle");
        assertNotSame(circle1, circle2, "每次获取应该是不同对象");
        assertEquals("Circle", circle1.getType());
        
        Prototype rect = registry.get("rectangle");
        assertNotNull(rect);
        assertEquals("Rectangle", rect.getType());
        
        // 获取不存在的原型
        Prototype notExist = registry.get("triangle");
        assertNull(notExist, "不存在的原型应返回 null");
    }

    // ==================== 附加题：序列化深拷贝 ====================
    
    /**
     * 【附加题】使用序列化实现深拷贝
     * 
     * 要求：
     * 1. 通过序列化/反序列化实现深拷贝
     * 2. 对象必须实现 Serializable 接口
     */
    public static class SerializablePrototype implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
        
        private String name;
        private java.util.List<String> tags;
        
        public SerializablePrototype(String name, java.util.List<String> tags) {
            this.name = name;
            this.tags = tags;
        }
        
        /**
         * 通过序列化实现深拷贝
         */
        public SerializablePrototype deepClone() {
            // TODO: 使用 ByteArrayOutputStream + ObjectOutputStream 序列化
            // TODO: 使用 ByteArrayInputStream + ObjectInputStream 反序列化
            // 提示：
            // 1. 写入到 ByteArrayOutputStream
            // 2. 从 ByteArrayInputStream 读取
            return null;
        }
        
        public String getName() { return name; }
        public java.util.List<String> getTags() { return tags; }
    }

    @Test
    @DisplayName("附加题：验证序列化深拷贝")
    void testSerializableDeepClone() {
        java.util.List<String> tags = new java.util.ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        
        SerializablePrototype original = new SerializablePrototype("Test", tags);
        SerializablePrototype cloned = original.deepClone();
        
        assertNotNull(cloned, "deepClone 不应返回 null");
        assertNotSame(original, cloned, "应该是不同对象");
        assertNotSame(original.getTags(), cloned.getTags(), "List 应该是不同对象");
        
        // 修改原对象不影响克隆对象
        original.getTags().add("tag3");
        assertEquals(2, cloned.getTags().size(), "克隆对象的 List 不应受影响");
    }
}
