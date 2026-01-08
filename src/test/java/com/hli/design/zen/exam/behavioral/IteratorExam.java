package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【迭代器模式考卷】
 * 场景：实现自定义集合的迭代
 */
@DisplayName("迭代器模式考卷")
public class IteratorExam {

    // ========== 题目1：迭代器接口 ==========
    
    public interface Iterator<T> {
        boolean hasNext();
        T next();
    }

    // ========== 题目2：容器接口 ==========
    
    public interface Container<T> {
        Iterator<T> createIterator();
    }

    // ========== 题目3：具体实现 ==========
    
    public static class BookShelf implements Container<String> {
        private List<String> books = new ArrayList<>();
        
        public void addBook(String book) {
            books.add(book);
        }
        
        @Override
        public Iterator<String> createIterator() {
            // TODO: 返回 BookIterator
            return null;
        }
        
        // 内部迭代器类
        private class BookIterator implements Iterator<String> {
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                // TODO
                return false;
            }
            
            @Override
            public String next() {
                // TODO
                return null;
            }
        }
    }

    @Test
    @DisplayName("验证迭代器")
    void testIterator() {
        BookShelf shelf = new BookShelf();
        shelf.addBook("Design Patterns");
        shelf.addBook("Clean Code");
        shelf.addBook("Refactoring");
        
        Iterator<String> iterator = shelf.createIterator();
        List<String> result = new ArrayList<>();
        
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        
        assertEquals(3, result.size());
        assertEquals("Design Patterns", result.get(0));
    }
}
