package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【中介者模式考卷】
 * 场景：实现聊天室
 */
@DisplayName("中介者模式考卷")
public class MediatorExam {

    // ========== 题目1：中介者接口 ==========
    
    public interface ChatMediator {
        void register(User user);
        void sendMessage(String message, User sender);
    }

    // ========== 题目2：同事类 ==========
    
    public static abstract class User {
        protected ChatMediator mediator;
        protected String name;
        protected List<String> receivedMessages = new ArrayList<>();
        
        public User(ChatMediator mediator, String name) {
            this.mediator = mediator;
            this.name = name;
        }
        
        public abstract void send(String message);
        public abstract void receive(String message);
        
        public String getName() { return name; }
        public List<String> getReceivedMessages() { return receivedMessages; }
    }
    
    public static class ChatUser extends User {
        public ChatUser(ChatMediator mediator, String name) {
            super(mediator, name);
        }
        
        @Override
        public void send(String message) {
            // TODO: 通过中介者发送消息
        }
        
        @Override
        public void receive(String message) {
            // TODO: 记录收到的消息
        }
    }

    // ========== 题目3：具体中介者 ==========
    
    public static class ChatRoom implements ChatMediator {
        private List<User> users = new ArrayList<>();
        
        @Override
        public void register(User user) {
            // TODO: 添加用户
        }
        
        @Override
        public void sendMessage(String message, User sender) {
            // TODO: 将消息发送给除发送者外的所有用户
        }
    }

    @Test
    @DisplayName("验证中介者")
    void testMediator() {
        ChatMediator chatRoom = new ChatRoom();
        
        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        User charlie = new ChatUser(chatRoom, "Charlie");
        
        chatRoom.register(alice);
        chatRoom.register(bob);
        chatRoom.register(charlie);
        
        alice.send("Hello everyone!");
        
        // Alice不应收到自己的消息
        assertEquals(0, alice.getReceivedMessages().size());
        // Bob和Charlie应收到消息
        assertEquals(1, bob.getReceivedMessages().size());
        assertEquals(1, charlie.getReceivedMessages().size());
    }
}
