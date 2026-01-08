package com.hli.design.zen.exam.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【工厂方法模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共3道题，满分100分
 * 2. 需要在标记 TODO 的地方补充代码
 * 3. 所有测试通过即为满分
 *
 * 验收标准：
 * - 题目1：实现简单工厂（反例，理解其局限性）
 * - 题目2：实现工厂方法模式
 * - 题目3：使用工厂方法创建不同产品
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("工厂方法模式考卷")
public class FactoryMethodExam {

    // ==================== 产品接口定义 ====================
    
    /**
     * 产品接口：日志记录器
     */
    public interface Logger {
        /**
         * 记录日志
         * @param message 日志消息
         * @return 格式化后的日志字符串
         */
        String log(String message);
    }

    // ==================== 题目1：简单工厂（反例）====================
    
    /**
     * 【题目1】实现简单工厂
     * 
     * 要求：
     * 1. 根据类型参数创建不同的 Logger 实现
     * 2. "file" -> FileLogger
     * 3. "console" -> ConsoleLogger
     * 4. 其他类型抛出 IllegalArgumentException
     */
    public static class SimpleLoggerFactory {
        
        public static Logger createLogger(String type) {
            // TODO: 根据 type 返回不同的 Logger 实现
            // 提示：使用 if-else 或 switch
            return null;
        }
    }
    
    /**
     * 文件日志记录器
     * TODO: 实现 Logger 接口
     */
    public static class FileLogger implements Logger {
        @Override
        public String log(String message) {
            // TODO: 返回格式 "[FILE] message"
            return null;
        }
    }
    
    /**
     * 控制台日志记录器
     * TODO: 实现 Logger 接口
     */
    public static class ConsoleLogger implements Logger {
        @Override
        public String log(String message) {
            // TODO: 返回格式 "[CONSOLE] message"
            return null;
        }
    }

    @Test
    @DisplayName("题目1：验证简单工厂")
    void testSimpleFactory() {
        Logger fileLogger = SimpleLoggerFactory.createLogger("file");
        Logger consoleLogger = SimpleLoggerFactory.createLogger("console");
        
        assertNotNull(fileLogger, "应创建 FileLogger");
        assertNotNull(consoleLogger, "应创建 ConsoleLogger");
        
        assertTrue(fileLogger instanceof FileLogger, "应该是 FileLogger 类型");
        assertTrue(consoleLogger instanceof ConsoleLogger, "应该是 ConsoleLogger 类型");
        
        assertEquals("[FILE] test", fileLogger.log("test"), "FileLogger 格式不正确");
        assertEquals("[CONSOLE] test", consoleLogger.log("test"), "ConsoleLogger 格式不正确");
        
        // 验证异常类型
        assertThrows(IllegalArgumentException.class, 
            () -> SimpleLoggerFactory.createLogger("unknown"),
            "未知类型应抛出 IllegalArgumentException");
    }

    // ==================== 题目2：工厂方法模式 ====================
    
    /**
     * 【题目2】定义工厂接口
     * 
     * 要求：
     * 定义抽象工厂接口，包含创建 Logger 的方法
     */
    public interface LoggerFactory {
        // TODO: 定义创建 Logger 的抽象方法
        Logger createLogger();
    }
    
    /**
     * 文件日志工厂
     * TODO: 实现 LoggerFactory 接口
     */
    public static class FileLoggerFactory implements LoggerFactory {
        @Override
        public Logger createLogger() {
            // TODO: 返回 FileLogger 实例
            return null;
        }
    }
    
    /**
     * 控制台日志工厂
     * TODO: 实现 LoggerFactory 接口
     */
    public static class ConsoleLoggerFactory implements LoggerFactory {
        @Override
        public Logger createLogger() {
            // TODO: 返回 ConsoleLogger 实例
            return null;
        }
    }

    @Test
    @DisplayName("题目2：验证工厂方法模式结构")
    void testFactoryMethod() {
        LoggerFactory fileFactory = new FileLoggerFactory();
        LoggerFactory consoleFactory = new ConsoleLoggerFactory();
        
        Logger fileLogger = fileFactory.createLogger();
        Logger consoleLogger = consoleFactory.createLogger();
        
        assertNotNull(fileLogger, "FileLoggerFactory 应创建 Logger");
        assertNotNull(consoleLogger, "ConsoleLoggerFactory 应创建 Logger");
        
        assertTrue(fileLogger instanceof FileLogger, "应该创建 FileLogger");
        assertTrue(consoleLogger instanceof ConsoleLogger, "应该创建 ConsoleLogger");
    }

    // ==================== 题目3：扩展新产品 ====================
    
    /**
     * 【题目3】扩展一个新的日志类型：数据库日志
     * 
     * 要求：
     * 1. 创建 DatabaseLogger 实现 Logger 接口
     * 2. 创建 DatabaseLoggerFactory 实现 LoggerFactory 接口
     * 3. log() 返回格式 "[DATABASE] message"
     * 
     * 思考：相比简单工厂，工厂方法如何实现开闭原则？
     */
    
    // TODO: 实现 DatabaseLogger
    public static class DatabaseLogger implements Logger {
        @Override
        public String log(String message) {
            // TODO: 返回格式 "[DATABASE] message"
            return null;
        }
    }
    
    // TODO: 实现 DatabaseLoggerFactory
    public static class DatabaseLoggerFactory implements LoggerFactory {
        @Override
        public Logger createLogger() {
            // TODO: 返回 DatabaseLogger 实例
            return null;
        }
    }

    @Test
    @DisplayName("题目3：验证扩展新产品（开闭原则）")
    void testExtension() {
        LoggerFactory dbFactory = new DatabaseLoggerFactory();
        Logger dbLogger = dbFactory.createLogger();
        
        assertNotNull(dbLogger, "DatabaseLoggerFactory 应创建 Logger");
        assertTrue(dbLogger instanceof DatabaseLogger, "应该创建 DatabaseLogger");
        assertEquals("[DATABASE] test", dbLogger.log("test"), "DatabaseLogger 格式不正确");
    }

    // ==================== 附加题：参数化工厂方法 ====================
    
    /**
     * 【附加题】实现参数化工厂方法
     * 
     * 要求：
     * 工厂方法接受配置参数，创建配置好的 Logger
     */
    public interface ConfigurableLoggerFactory {
        Logger createLogger(String config);
    }
    
    public static class ConfigurableFileLoggerFactory implements ConfigurableLoggerFactory {
        @Override
        public Logger createLogger(String config) {
            // TODO: 创建带配置的 FileLogger
            // 提示：可以在 log() 输出时包含 config 信息
            // 格式：[FILE:config] message
            return null;
        }
    }

    @Test
    @DisplayName("附加题：验证参数化工厂方法")
    void testConfigurableFactory() {
        ConfigurableLoggerFactory factory = new ConfigurableFileLoggerFactory();
        Logger logger = factory.createLogger("/var/log/app.log");
        
        assertNotNull(logger, "应创建 Logger");
        String result = logger.log("test");
        assertTrue(result.contains("/var/log/app.log"), 
            "日志应包含配置信息，实际输出: " + result);
    }
}
