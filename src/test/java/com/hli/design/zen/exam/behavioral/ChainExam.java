package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【责任链模式考卷】
 * 场景：实现请假审批流程
 */
@DisplayName("责任链模式考卷")
public class ChainExam {

    // ========== 题目1：请求类 ==========
    
    public static class LeaveRequest {
        private String name;
        private int days;
        
        public LeaveRequest(String name, int days) {
            this.name = name;
            this.days = days;
        }
        
        public String getName() { return name; }
        public int getDays() { return days; }
    }

    // ========== 题目2：处理者抽象类 ==========
    
    public static abstract class Approver {
        protected Approver next;
        protected String title;
        
        public Approver(String title) { this.title = title; }
        
        public void setNext(Approver next) {
            // TODO
        }
        
        public abstract String processRequest(LeaveRequest request);
        
        protected String passToNext(LeaveRequest request) {
            if (next != null) {
                return next.processRequest(request);
            }
            return "Request denied - no approver";
        }
    }

    // ========== 题目3：具体处理者 ==========
    
    // 组长：可批准1天
    public static class TeamLeader extends Approver {
        public TeamLeader() { super("TeamLeader"); }
        
        @Override
        public String processRequest(LeaveRequest request) {
            // TODO: 如果 days <= 1，批准；否则传递给下一个
            return null;
        }
    }
    
    // 经理：可批准3天
    public static class Manager extends Approver {
        public Manager() { super("Manager"); }
        
        @Override
        public String processRequest(LeaveRequest request) {
            // TODO: 如果 days <= 3，批准；否则传递
            return null;
        }
    }
    
    // 总监：可批准7天
    public static class Director extends Approver {
        public Director() { super("Director"); }
        
        @Override
        public String processRequest(LeaveRequest request) {
            // TODO: 如果 days <= 7，批准；否则传递
            return null;
        }
    }

    @Test
    @DisplayName("验证责任链")
    void testChain() {
        Approver leader = new TeamLeader();
        Approver manager = new Manager();
        Approver director = new Director();
        
        leader.setNext(manager);
        manager.setNext(director);
        
        // 1天由组长批准
        String result1 = leader.processRequest(new LeaveRequest("Alice", 1));
        assertTrue(result1.contains("TeamLeader") || result1.contains("approved"));
        
        // 3天由经理批准
        String result2 = leader.processRequest(new LeaveRequest("Bob", 3));
        assertTrue(result2.contains("Manager") || result2.contains("approved"));
        
        // 7天由总监批准
        String result3 = leader.processRequest(new LeaveRequest("Carol", 7));
        assertTrue(result3.contains("Director") || result3.contains("approved"));
        
        // 10天无人能批
        String result4 = leader.processRequest(new LeaveRequest("Dave", 10));
        assertTrue(result4.contains("denied"));
    }
}
