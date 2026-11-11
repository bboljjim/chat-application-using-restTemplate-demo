package com.chat.host1.mythread;

import java.util.Scanner;

import com.chat.host1.data.ChatBox;

public class SendThread extends Thread{

    private ChatBox sendBox;
    private Scanner scanner;
    private String sendMsg;

    public SendThread(ChatBox sendBox){
        this.sendBox = sendBox;
        this.scanner = new Scanner(System.in);
        this.sendMsg = new String();
    }

    @Override 
    public void run(){
        while(!sendMsg.equals("bye")){
            sendMsg = scanner.nextLine();
            sendBox.push(sendMsg);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
