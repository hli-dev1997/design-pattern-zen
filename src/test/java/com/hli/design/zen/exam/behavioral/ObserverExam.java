package com.hli.design.zen.exam.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 【观察者模式考卷】
 * 场景：实现股票价格变动通知
 */
@DisplayName("观察者模式考卷")
public class ObserverExam {

    // ========== 题目1：观察者接口 ==========
    
    public interface Observer {
        void update(String stockCode, double price);
    }
    
    public static class StockTrader implements Observer {
        private String name;
        private String lastNotification;
        
        public StockTrader(String name) { this.name = name; }
        
        @Override
        public void update(String stockCode, double price) {
            // TODO: 记录通知内容
            // lastNotification = name + " received: " + stockCode + " -> " + price
        }
        
        public String getLastNotification() { return lastNotification; }
    }

    // ========== 题目2：被观察者（主题）==========
    
    public static class StockMarket {
        private List<Observer> observers = new ArrayList<>();
        private String stockCode;
        private double price;
        
        public void addObserver(Observer observer) {
            // TODO
        }
        
        public void removeObserver(Observer observer) {
            // TODO
        }
        
        public void setStockPrice(String stockCode, double price) {
            this.stockCode = stockCode;
            this.price = price;
            // TODO: 通知所有观察者
        }
        
        private void notifyObservers() {
            // TODO: 遍历观察者列表，调用update
        }
        
        public int getObserverCount() { return observers.size(); }
    }

    @Test
    @DisplayName("验证观察者通知")
    void testObserver() {
        StockMarket market = new StockMarket();
        StockTrader trader1 = new StockTrader("Trader1");
        StockTrader trader2 = new StockTrader("Trader2");
        
        market.addObserver(trader1);
        market.addObserver(trader2);
        assertEquals(2, market.getObserverCount());
        
        market.setStockPrice("AAPL", 150.0);
        
        assertNotNull(trader1.getLastNotification());
        assertTrue(trader1.getLastNotification().contains("AAPL"));
        assertTrue(trader2.getLastNotification().contains("150"));
        
        market.removeObserver(trader1);
        assertEquals(1, market.getObserverCount());
    }
}
