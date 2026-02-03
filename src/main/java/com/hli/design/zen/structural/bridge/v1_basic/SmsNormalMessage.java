package com.hli.design.zen.structural.bridge.v1_basic;

// 这种代码写出来，第二天因为左脚先迈进公司被开除
class SmsNormalMessage {
    public void send() {
        System.out.println("发送普通短信");
    }
}

class SmsUrgentMessage {
    public void send() {
        System.out.println("发送加急短信");
    }
}

class EmailNormalMessage {
    public void send() {
        System.out.println("发送普通邮件");
    }
}

class EmailUrgentMessage {
    public void send() {
        System.out.println("发送加急邮件");
    }
}
// 还没完...如果来了个App推送，你得再加两个类...
// 如果再来个“特急”，你还得再加3个类...