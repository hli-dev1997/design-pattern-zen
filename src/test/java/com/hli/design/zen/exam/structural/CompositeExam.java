package com.hli.design.zen.exam.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【组合模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共2道题，满分100分
 * 2. 场景：实现文件系统的树形结构
 *
 * 验收标准：
 * - 题目1：实现组件接口、叶子节点和组合节点
 * - 题目2：实现递归操作
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("组合模式考卷")
public class CompositeExam {

    // ==================== 题目1：组合模式结构 ====================
    
    /**
     * 【题目1-1】文件系统组件接口
     */
    public interface FileComponent {
        String getName();
        long getSize();
        void print(String prefix);  // 打印结构
    }
    
    /**
     * 【题目1-2】文件类（叶子节点）
     * TODO: 实现 FileComponent 接口
     */
    public static class File implements FileComponent {
        private String name;
        private long size;
        
        public File(String name, long size) {
            this.name = name;
            this.size = size;
        }
        
        @Override
        public String getName() {
            // TODO
            return null;
        }
        
        @Override
        public long getSize() {
            // TODO
            return 0;
        }
        
        @Override
        public void print(String prefix) {
            // TODO: 打印格式 "prefix- name (size bytes)"
            System.out.println(prefix + "- " + name + " (" + size + " bytes)");
        }
    }
    
    /**
     * 【题目1-3】文件夹类（组合节点）
     * TODO: 实现 FileComponent 接口
     */
    public static class Folder implements FileComponent {
        private String name;
        private List<FileComponent> children = new ArrayList<>();
        
        public Folder(String name) {
            this.name = name;
        }
        
        public void add(FileComponent component) {
            // TODO: 添加子节点
        }
        
        public void remove(FileComponent component) {
            // TODO: 移除子节点
        }
        
        @Override
        public String getName() {
            // TODO
            return null;
        }
        
        @Override
        public long getSize() {
            // TODO: 递归计算所有子节点的大小总和
            return 0;
        }
        
        @Override
        public void print(String prefix) {
            // TODO: 
            // 1. 打印自己 "prefix+ name/"
            // 2. 递归打印所有子节点（prefix + "  "）
        }
        
        public List<FileComponent> getChildren() {
            return children;
        }
    }

    @Test
    @DisplayName("题目1：验证组合模式结构")
    void testCompositeStructure() {
        // 创建文件
        File file1 = new File("readme.txt", 100);
        File file2 = new File("main.java", 500);
        File file3 = new File("test.java", 300);
        
        assertEquals("readme.txt", file1.getName());
        assertEquals(100, file1.getSize());
        
        // 创建文件夹
        Folder src = new Folder("src");
        src.add(file2);
        src.add(file3);
        
        assertEquals("src", src.getName());
        assertEquals(800, src.getSize(), "文件夹大小应为子文件之和");
        
        // 嵌套文件夹
        Folder root = new Folder("project");
        root.add(file1);
        root.add(src);
        
        assertEquals(900, root.getSize(), "根目录大小应为所有文件之和");
    }

    // ==================== 题目2：递归操作 ====================
    
    /**
     * 【题目2】实现文件搜索功能
     * 
     * 要求：递归搜索指定扩展名的文件
     */
    public static class FileSearcher {
        
        /**
         * 搜索指定扩展名的文件
         * @param component 根组件
         * @param extension 扩展名（如 ".java"）
         * @return 匹配的文件列表
         */
        public List<File> search(FileComponent component, String extension) {
            List<File> result = new ArrayList<>();
            // TODO: 实现递归搜索
            // 提示：
            // 1. 如果是 File，检查名称是否以 extension 结尾
            // 2. 如果是 Folder，递归搜索所有子节点
            return result;
        }
        
        /**
         * 计算文件数量
         */
        public int countFiles(FileComponent component) {
            // TODO: 递归计算叶子节点（File）数量
            return 0;
        }
    }

    @Test
    @DisplayName("题目2：验证递归操作")
    void testRecursiveOperations() {
        // 构建文件结构
        File readme = new File("readme.txt", 100);
        File main = new File("Main.java", 500);
        File test = new File("Test.java", 300);
        File config = new File("config.xml", 200);
        
        Folder src = new Folder("src");
        src.add(main);
        
        Folder testFolder = new Folder("test");
        testFolder.add(test);
        
        Folder root = new Folder("project");
        root.add(readme);
        root.add(config);
        root.add(src);
        root.add(testFolder);
        
        FileSearcher searcher = new FileSearcher();
        
        // 搜索 .java 文件
        List<File> javaFiles = searcher.search(root, ".java");
        assertEquals(2, javaFiles.size(), "应找到2个Java文件");
        
        // 搜索 .txt 文件
        List<File> txtFiles = searcher.search(root, ".txt");
        assertEquals(1, txtFiles.size(), "应找到1个txt文件");
        
        // 计算文件数量
        int fileCount = searcher.countFiles(root);
        assertEquals(4, fileCount, "应有4个文件");
    }

    @Test
    @DisplayName("综合测试：打印文件结构")
    void testPrintStructure() {
        File readme = new File("readme.txt", 100);
        File main = new File("Main.java", 500);
        
        Folder src = new Folder("src");
        src.add(main);
        
        Folder root = new Folder("project");
        root.add(readme);
        root.add(src);
        
        // 打印结构（观察控制台输出）
        System.out.println("=== File Structure ===");
        root.print("");
        
        // 基本验证
        assertNotNull(root.getChildren());
        assertEquals(2, root.getChildren().size());
    }
}
