package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【命令模式考卷】
 * 场景：实现文本编辑器的撤销重做
 */
@DisplayName("命令模式考卷")
public class CommandExam {

    // ========== 题目1：命令接口 ==========
    
    public interface Command {
        void execute();
        void undo();
    }

    // ========== 题目2：接收者 ==========
    
    public static class TextEditor {
        private StringBuilder text = new StringBuilder();
        
        public void append(String s) { text.append(s); }
        public void delete(int length) {
            if (length <= text.length()) {
                text.delete(text.length() - length, text.length());
            }
        }
        public String getText() { return text.toString(); }
    }

    // ========== 题目3：具体命令 ==========
    
    public static class AppendCommand implements Command {
        private TextEditor editor;
        private String text;
        
        public AppendCommand(TextEditor editor, String text) {
            this.editor = editor;
            this.text = text;
        }
        
        @Override
        public void execute() {
            // TODO: 调用editor.append
        }
        
        @Override
        public void undo() {
            // TODO: 调用editor.delete
        }
    }

    // ========== 题目4：调用者 ==========
    
    public static class CommandInvoker {
        private List<Command> history = new ArrayList<>();
        private int current = -1;
        
        public void execute(Command cmd) {
            // TODO: 执行命令并记录历史
        }
        
        public void undo() {
            // TODO: 撤销当前命令
        }
        
        public void redo() {
            // TODO: 重做命令
        }
    }

    @Test
    @DisplayName("验证命令撤销重做")
    void testCommand() {
        TextEditor editor = new TextEditor();
        CommandInvoker invoker = new CommandInvoker();
        
        invoker.execute(new AppendCommand(editor, "Hello"));
        assertEquals("Hello", editor.getText());
        
        invoker.execute(new AppendCommand(editor, " World"));
        assertEquals("Hello World", editor.getText());
        
        invoker.undo();
        assertEquals("Hello", editor.getText());
        
        invoker.redo();
        assertEquals("Hello World", editor.getText());
    }
}
