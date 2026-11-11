package com.chat.host1.mythread;

import com.chat.host1.data.ChatBox;

public class PrintThread extends Thread{

    private ChatBox receiveBox;
    private String printMsg;

    public PrintThread(ChatBox receiveBox){
        this.receiveBox = receiveBox;
        this.printMsg = null;
    }

    @Override 
    public void run(){
        while(true){
            if(!receiveBox.isEmpty()){
                printMsg = receiveBox.pop();
                if(printMsg != null)
                    System.out.println("[host2] : " + printMsg);
                if(printMsg.equals("bye")){
                    System.out.println("상대방이 채팅방에서 나갔습니다. 채팅이 종료되었습니다.");
                    break;
                }
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}