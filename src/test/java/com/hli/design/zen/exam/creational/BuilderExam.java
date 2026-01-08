package com.hli.design.zen.exam.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【建造者模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共3道题，满分100分
 * 2. 场景：构建复杂的电脑配置对象
 *
 * 验收标准：
 * - 题目1：实现产品类（Computer）
 * - 题目2：实现建造者（ComputerBuilder）
 * - 题目3：实现指挥者（Director）
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("建造者模式考卷")
public class BuilderExam {

    // ==================== 题目1：产品类 ====================
    
    /**
     * 【题目1】实现电脑产品类
     * 
     * 要求：
     * 1. 包含属性：cpu, ram, storage, gpu（显卡，可选）
     * 2. 所有属性都有 getter 方法
     * 3. toString() 返回配置描述
     */
    public static class Computer {
        // TODO: 定义属性
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;  // 可选
        
        // TODO: 构造函数（由 Builder 调用）
        
        // TODO: getter 方法
        public String getCpu() { return cpu; }
        public String getRam() { return ram; }
        public String getStorage() { return storage; }
        public String getGpu() { return gpu; }
        
        // TODO: setter 方法（由 Builder 调用）
        public void setCpu(String cpu) { this.cpu = cpu; }
        public void setRam(String ram) { this.ram = ram; }
        public void setStorage(String storage) { this.storage = storage; }
        public void setGpu(String gpu) { this.gpu = gpu; }
        
        @Override
        public String toString() {
            // TODO: 返回格式 "Computer[cpu=xxx, ram=xxx, storage=xxx, gpu=xxx]"
            return null;
        }
    }

    @Test
    @DisplayName("题目1：验证产品类")
    void testProduct() {
        Computer computer = new Computer();
        computer.setCpu("Intel i9");
        computer.setRam("32GB");
        computer.setStorage("1TB SSD");
        computer.setGpu("RTX 4090");
        
        assertEquals("Intel i9", computer.getCpu());
        assertEquals("32GB", computer.getRam());
        assertEquals("1TB SSD", computer.getStorage());
        assertEquals("RTX 4090", computer.getGpu());
        
        String str = computer.toString();
        assertNotNull(str, "toString() 不应返回 null");
        assertTrue(str.contains("Intel i9"), "toString 应包含 CPU 信息");
    }

    // ==================== 题目2：建造者 ====================
    
    /**
     * 【题目2】实现电脑建造者
     * 
     * 要求：
     * 1. 支持链式调用（每个 set 方法返回 this）
     * 2. build() 方法返回构建好的 Computer
     */
    public static class ComputerBuilder {
        private Computer computer;
        
        public ComputerBuilder() {
            // TODO: 初始化 Computer 对象
        }
        
        public ComputerBuilder cpu(String cpu) {
            // TODO: 设置 CPU，返回 this 支持链式调用
            return null;
        }
        
        public ComputerBuilder ram(String ram) {
            // TODO: 设置 RAM，返回 this
            return null;
        }
        
        public ComputerBuilder storage(String storage) {
            // TODO: 设置存储，返回 this
            return null;
        }
        
        public ComputerBuilder gpu(String gpu) {
            // TODO: 设置显卡（可选），返回 this
            return null;
        }
        
        public Computer build() {
            // TODO: 返回构建好的 Computer
            return null;
        }
    }

    @Test
    @DisplayName("题目2：验证建造者链式调用")
    void testBuilder() {
        Computer gaming = new ComputerBuilder()
            .cpu("Intel i9-13900K")
            .ram("64GB DDR5")
            .storage("2TB NVMe SSD")
            .gpu("RTX 4090")
            .build();
        
        assertNotNull(gaming, "build() 不应返回 null");
        assertEquals("Intel i9-13900K", gaming.getCpu());
        assertEquals("64GB DDR5", gaming.getRam());
        assertEquals("2TB NVMe SSD", gaming.getStorage());
        assertEquals("RTX 4090", gaming.getGpu());
        
        // 测试可选属性（不设置 GPU）
        Computer office = new ComputerBuilder()
            .cpu("Intel i5")
            .ram("16GB")
            .storage("512GB SSD")
            .build();
        
        assertNotNull(office);
        assertNull(office.getGpu(), "未设置 GPU 应该为 null");
    }

    // ==================== 题目3：指挥者 ====================
    
    /**
     * 【题目3】实现指挥者（预设配置）
     * 
     * 要求：
     * 1. buildGamingComputer(): 游戏配置（i9, 64GB, 2TB, RTX4090）
     * 2. buildOfficeComputer(): 办公配置（i5, 16GB, 512GB, 无显卡）
     * 3. buildDeveloperComputer(): 开发配置（i7, 32GB, 1TB, RTX3060）
     */
    public static class ComputerDirector {
        private ComputerBuilder builder;
        
        public ComputerDirector(ComputerBuilder builder) {
            this.builder = builder;
        }
        
        public Computer buildGamingComputer() {
            // TODO: 构建游戏配置
            // CPU: "Intel i9-13900K"
            // RAM: "64GB DDR5"
            // Storage: "2TB NVMe SSD"
            // GPU: "RTX 4090"
            return null;
        }
        
        public Computer buildOfficeComputer() {
            // TODO: 构建办公配置
            // CPU: "Intel i5-13400"
            // RAM: "16GB DDR4"
            // Storage: "512GB SSD"
            // GPU: null（不设置）
            return null;
        }
        
        public Computer buildDeveloperComputer() {
            // TODO: 构建开发配置
            // CPU: "Intel i7-13700K"
            // RAM: "32GB DDR5"
            // Storage: "1TB NVMe SSD"
            // GPU: "RTX 3060"
            return null;
        }
    }

    @Test
    @DisplayName("题目3：验证指挥者预设配置")
    void testDirector() {
        ComputerDirector director = new ComputerDirector(new ComputerBuilder());
        
        // 游戏配置
        Computer gaming = director.buildGamingComputer();
        assertNotNull(gaming);
        assertEquals("Intel i9-13900K", gaming.getCpu());
        assertEquals("RTX 4090", gaming.getGpu());
        
        // 办公配置
        Computer office = director.buildOfficeComputer();
        assertNotNull(office);
        assertEquals("Intel i5-13400", office.getCpu());
        assertNull(office.getGpu(), "办公电脑不应有独立显卡");
        
        // 开发配置
        Computer dev = director.buildDeveloperComputer();
        assertNotNull(dev);
        assertEquals("32GB DDR5", dev.getRam());
    }

    // ==================== 附加题：静态内部类 Builder ====================
    
    /**
     * 【附加题】实现 Lombok 风格的 Builder
     * 
     * 要求：
     * 1. Builder 作为静态内部类
     * 2. 产品类构造函数私有
     * 3. 通过静态 builder() 方法获取 Builder
     */
    public static class Server {
        private final String hostname;
        private final int cores;
        private final int memoryGB;
        private final boolean ssdEnabled;
        
        // TODO: 私有构造函数，接收 Builder 参数
        private Server(Builder builder) {
            this.hostname = builder.hostname;
            this.cores = builder.cores;
            this.memoryGB = builder.memoryGB;
            this.ssdEnabled = builder.ssdEnabled;
        }
        
        // TODO: 静态 builder() 方法
        public static Builder builder() {
            return null;  // TODO
        }
        
        // Getter 方法
        public String getHostname() { return hostname; }
        public int getCores() { return cores; }
        public int getMemoryGB() { return memoryGB; }
        public boolean isSsdEnabled() { return ssdEnabled; }
        
        // TODO: 实现静态内部类 Builder
        public static class Builder {
            private String hostname;
            private int cores;
            private int memoryGB;
            private boolean ssdEnabled;
            
            public Builder hostname(String hostname) {
                // TODO
                return null;
            }
            
            public Builder cores(int cores) {
                // TODO
                return null;
            }
            
            public Builder memoryGB(int memoryGB) {
                // TODO
                return null;
            }
            
            public Builder ssdEnabled(boolean ssdEnabled) {
                // TODO
                return null;
            }
            
            public Server build() {
                // TODO: 返回 new Server(this)
                return null;
            }
        }
    }

    @Test
    @DisplayName("附加题：验证静态内部类 Builder")
    void testStaticInnerBuilder() {
        Server server = Server.builder()
            .hostname("web-server-01")
            .cores(32)
            .memoryGB(128)
            .ssdEnabled(true)
            .build();
        
        assertNotNull(server);
        assertEquals("web-server-01", server.getHostname());
        assertEquals(32, server.getCores());
        assertEquals(128, server.getMemoryGB());
        assertTrue(server.isSsdEnabled());
    }
}
