package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【访问者模式考卷】
 * 场景：实现员工薪资计算
 */
@DisplayName("访问者模式考卷")
public class VisitorExam {

    // ========== 题目1：元素接口 ==========
    
    public interface Employee {
        void accept(Visitor visitor);
        String getName();
        double getBaseSalary();
    }

    // ========== 题目2：具体元素 ==========
    
    public static class Engineer implements Employee {
        private String name;
        private double baseSalary;
        
        public Engineer(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }
        
        @Override
        public void accept(Visitor visitor) {
            // TODO: visitor.visit(this)
        }
        
        @Override
        public String getName() { return name; }
        @Override
        public double getBaseSalary() { return baseSalary; }
    }
    
    public static class Manager implements Employee {
        private String name;
        private double baseSalary;
        
        public Manager(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }
        
        @Override
        public void accept(Visitor visitor) {
            // TODO: visitor.visit(this)
        }
        
        @Override
        public String getName() { return name; }
        @Override
        public double getBaseSalary() { return baseSalary; }
    }

    // ========== 题目3：访问者接口 ==========
    
    public interface Visitor {
        void visit(Engineer engineer);
        void visit(Manager manager);
        double getTotalSalary();
    }

    // ========== 题目4：具体访问者 ==========
    
    public static class SalaryCalculator implements Visitor {
        private double totalSalary = 0;
        
        @Override
        public void visit(Engineer engineer) {
            // TODO: 工程师薪资 = 基本工资 * 1.2
            // totalSalary += ...
        }
        
        @Override
        public void visit(Manager manager) {
            // TODO: 经理薪资 = 基本工资 * 1.5
            // totalSalary += ...
        }
        
        @Override
        public double getTotalSalary() { return totalSalary; }
    }

    @Test
    @DisplayName("验证访问者")
    void testVisitor() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Engineer("Alice", 10000));
        employees.add(new Engineer("Bob", 12000));
        employees.add(new Manager("Charlie", 20000));
        
        SalaryCalculator calculator = new SalaryCalculator();
        for (Employee emp : employees) {
            emp.accept(calculator);
        }
        
        // 10000*1.2 + 12000*1.2 + 20000*1.5 = 56400
        assertEquals(56400, calculator.getTotalSalary(), 0.01);
    }
}
