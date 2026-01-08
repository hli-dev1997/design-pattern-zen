package com.hli.design.zen.exam.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 【门面模式考卷】
 *
 * 考试说明：
 * 1. 本考卷共2道题，满分100分
 * 2. 场景：实现家庭影院系统的统一控制
 *
 * 验收标准：
 * - 题目1：定义子系统类
 * - 题目2：实现门面类统一控制
 *
 * @author 考生请填写姓名
 * @date 考试日期
 */
@DisplayName("门面模式考卷")
public class FacadeExam {

    // ==================== 题目1：子系统定义 ====================
    
    /**
     * 【题目1-1】DVD播放器
     */
    public static class DvdPlayer {
        private boolean on = false;
        private String movie = null;
        
        public void on() {
            // TODO: 开机
            on = true;
        }
        
        public void off() {
            // TODO: 关机
            on = false;
            movie = null;
        }
        
        public void play(String movie) {
            // TODO: 播放电影
            this.movie = movie;
        }
        
        public void stop() {
            // TODO: 停止播放
            movie = null;
        }
        
        public boolean isOn() { return on; }
        public String getCurrentMovie() { return movie; }
    }
    
    /**
     * 【题目1-2】投影仪
     */
    public static class Projector {
        private boolean on = false;
        private String mode = "standard";
        
        public void on() {
            // TODO
            on = true;
        }
        
        public void off() {
            // TODO
            on = false;
        }
        
        public void wideScreenMode() {
            // TODO: 设置宽屏模式
            mode = "widescreen";
        }
        
        public boolean isOn() { return on; }
        public String getMode() { return mode; }
    }
    
    /**
     * 【题目1-3】音响系统
     */
    public static class SoundSystem {
        private boolean on = false;
        private int volume = 0;
        
        public void on() {
            // TODO
            on = true;
        }
        
        public void off() {
            // TODO
            on = false;
            volume = 0;
        }
        
        public void setVolume(int volume) {
            // TODO
            this.volume = volume;
        }
        
        public boolean isOn() { return on; }
        public int getVolume() { return volume; }
    }
    
    /**
     * 【题目1-4】灯光
     */
    public static class Lights {
        private int brightness = 100;
        
        public void dim(int level) {
            // TODO: 调暗灯光
            brightness = level;
        }
        
        public void on() {
            // TODO: 全亮
            brightness = 100;
        }
        
        public int getBrightness() { return brightness; }
    }

    @Test
    @DisplayName("题目1：验证子系统")
    void testSubsystems() {
        DvdPlayer dvd = new DvdPlayer();
        dvd.on();
        dvd.play("Inception");
        assertTrue(dvd.isOn());
        assertEquals("Inception", dvd.getCurrentMovie());
        
        Projector projector = new Projector();
        projector.on();
        projector.wideScreenMode();
        assertTrue(projector.isOn());
        assertEquals("widescreen", projector.getMode());
        
        SoundSystem sound = new SoundSystem();
        sound.on();
        sound.setVolume(50);
        assertTrue(sound.isOn());
        assertEquals(50, sound.getVolume());
    }

    // ==================== 题目2：门面实现 ====================
    
    /**
     * 【题目2】家庭影院门面
     * 
     * 要求：
     * 1. 持有所有子系统的引用
     * 2. watchMovie(): 一键开始看电影
     * 3. endMovie(): 一键结束
     */
    public static class HomeTheaterFacade {
        private DvdPlayer dvd;
        private Projector projector;
        private SoundSystem sound;
        private Lights lights;
        
        public HomeTheaterFacade(DvdPlayer dvd, Projector projector, 
                                  SoundSystem sound, Lights lights) {
            // TODO: 保存所有子系统引用
        }
        
        /**
         * 一键看电影
         * 操作顺序：
         * 1. 调暗灯光到10%
         * 2. 打开投影仪，设置宽屏模式
         * 3. 打开音响，设置音量为60
         * 4. 打开DVD，播放电影
         */
        public void watchMovie(String movie) {
            // TODO: 实现一键看电影
        }
        
        /**
         * 一键结束
         * 操作顺序：
         * 1. 停止DVD并关闭
         * 2. 关闭音响
         * 3. 关闭投影仪
         * 4. 灯光全亮
         */
        public void endMovie() {
            // TODO: 实现一键结束
        }
        
        // 提供子系统访问（用于测试）
        public DvdPlayer getDvd() { return dvd; }
        public Projector getProjector() { return projector; }
        public SoundSystem getSound() { return sound; }
        public Lights getLights() { return lights; }
    }

    @Test
    @DisplayName("题目2：验证门面统一控制")
    void testFacade() {
        DvdPlayer dvd = new DvdPlayer();
        Projector projector = new Projector();
        SoundSystem sound = new SoundSystem();
        Lights lights = new Lights();
        
        HomeTheaterFacade theater = new HomeTheaterFacade(dvd, projector, sound, lights);
        
        // 一键看电影
        theater.watchMovie("The Matrix");
        
        assertEquals(10, lights.getBrightness(), "灯光应调暗到10%");
        assertTrue(projector.isOn(), "投影仪应开启");
        assertEquals("widescreen", projector.getMode(), "投影仪应为宽屏模式");
        assertTrue(sound.isOn(), "音响应开启");
        assertEquals(60, sound.getVolume(), "音量应为60");
        assertTrue(dvd.isOn(), "DVD应开启");
        assertEquals("The Matrix", dvd.getCurrentMovie(), "应播放指定电影");
        
        // 一键结束
        theater.endMovie();
        
        assertFalse(dvd.isOn(), "DVD应关闭");
        assertNull(dvd.getCurrentMovie(), "应停止播放");
        assertFalse(sound.isOn(), "音响应关闭");
        assertFalse(projector.isOn(), "投影仪应关闭");
        assertEquals(100, lights.getBrightness(), "灯光应全亮");
    }

    // ==================== 附加题：多级门面 ====================
    
    /**
     * 【附加题】实现带模式的门面
     * 
     * 要求：支持不同的观影模式
     * - movie: 电影模式（灯光10%，音量60）
     * - music: 音乐模式（灯光50%，只开音响，音量80）
     * - game: 游戏模式（灯光30%，音量40）
     */
    public static class SmartHomeTheater extends HomeTheaterFacade {
        
        public SmartHomeTheater(DvdPlayer dvd, Projector projector, 
                                 SoundSystem sound, Lights lights) {
            super(dvd, projector, sound, lights);
        }
        
        public void setMode(String mode) {
            // TODO: 根据模式设置不同参数
        }
    }
}
