package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【备忘录模式考卷】
 * 场景：实现游戏存档功能
 */
@DisplayName("备忘录模式考卷")
public class MementoExam {

    // ========== 题目1：备忘录类 ==========
    
    public static class GameMemento {
        private final int level;
        private final int health;
        private final int score;
        
        public GameMemento(int level, int health, int score) {
            this.level = level;
            this.health = health;
            this.score = score;
        }
        
        public int getLevel() { return level; }
        public int getHealth() { return health; }
        public int getScore() { return score; }
    }

    // ========== 题目2：发起人（游戏角色）==========
    
    public static class GameCharacter {
        private int level;
        private int health;
        private int score;
        
        public GameCharacter() {
            this.level = 1;
            this.health = 100;
            this.score = 0;
        }
        
        public void play() {
            level++;
            health -= 10;
            score += 100;
        }
        
        public GameMemento save() {
            // TODO: 创建并返回备忘录
            return null;
        }
        
        public void restore(GameMemento memento) {
            // TODO: 从备忘录恢复状态
        }
        
        public int getLevel() { return level; }
        public int getHealth() { return health; }
        public int getScore() { return score; }
    }

    // ========== 题目3：管理者 ==========
    
    public static class GameSaveManager {
        private Stack<GameMemento> saves = new Stack<>();
        
        public void save(GameMemento memento) {
            // TODO: 压入栈
        }
        
        public GameMemento load() {
            // TODO: 弹出栈顶
            return null;
        }
        
        public boolean hasSaves() {
            return !saves.isEmpty();
        }
    }

    @Test
    @DisplayName("验证备忘录")
    void testMemento() {
        GameCharacter character = new GameCharacter();
        GameSaveManager manager = new GameSaveManager();
        
        // 玩一会
        character.play();
        assertEquals(2, character.getLevel());
        
        // 存档
        manager.save(character.save());
        
        // 继续玩
        character.play();
        character.play();
        assertEquals(4, character.getLevel());
        assertEquals(70, character.getHealth());
        
        // 读档
        character.restore(manager.load());
        assertEquals(2, character.getLevel());
        assertEquals(90, character.getHealth());
    }
}
