package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【模板方法模式考卷】
 * 场景：实现数据导出流程
 */
@DisplayName("模板方法模式考卷")
public class TemplateExam {

    // ========== 题目1：抽象模板类 ==========
    
    public static abstract class DataExporter {
        
        // 模板方法（final防止子类覆盖）
        public final String export() {
            String data = fetchData();
            String processed = processData(data);
            String result = formatOutput(processed);
            if (needCompress()) {  // 钩子方法
                result = compress(result);
            }
            return result;
        }
        
        // 抽象方法：由子类实现
        protected abstract String fetchData();
        protected abstract String processData(String data);
        protected abstract String formatOutput(String data);
        
        // 钩子方法：子类可选择覆盖
        protected boolean needCompress() { return false; }
        
        private String compress(String data) {
            return "COMPRESSED:" + data;
        }
    }

    // ========== 题目2：具体实现 ==========
    
    public static class CsvExporter extends DataExporter {
        @Override
        protected String fetchData() {
            // TODO: 返回 "raw data"
            return null;
        }
        
        @Override
        protected String processData(String data) {
            // TODO: 返回 "processed:" + data
            return null;
        }
        
        @Override
        protected String formatOutput(String data) {
            // TODO: 返回 "CSV:" + data
            return null;
        }
    }
    
    public static class JsonExporter extends DataExporter {
        @Override
        protected String fetchData() {
            return "raw json"; // TODO
        }
        
        @Override
        protected String processData(String data) {
            return "processed:" + data; // TODO
        }
        
        @Override
        protected String formatOutput(String data) {
            return "JSON:" + data; // TODO
        }
        
        @Override
        protected boolean needCompress() {
            return true;  // 覆盖钩子方法
        }
    }

    @Test
    @DisplayName("验证模板方法")
    void testTemplate() {
        DataExporter csv = new CsvExporter();
        String csvResult = csv.export();
        assertTrue(csvResult.contains("CSV"));
        assertFalse(csvResult.contains("COMPRESSED"));
        
        DataExporter json = new JsonExporter();
        String jsonResult = json.export();
        assertTrue(jsonResult.contains("JSON"));
        assertTrue(jsonResult.contains("COMPRESSED"));
    }
}
