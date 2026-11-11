package com.chat.host2.mythread;

import com.chat.host2.data.ChatBox;
import com.chat.host2.service.myRestTemplateService;

public class ReceiveThread extends Thread{
    
    private myRestTemplateService restTemplateService;
    private ChatBox receiveBox;
    private String receiveMsg;

    public ReceiveThread(myRestTemplateService restTemplateService, ChatBox receiveBox){
        this.restTemplateService = restTemplateService;
        this.receiveBox = receiveBox;
        this.receiveMsg = new String();
    }

    @Override 
    public void run(){
        while(!receiveMsg.equals("bye")){
            receiveMsg = restTemplateService.requestMSG();
            if(!receiveMsg.equals("null"))
                receiveBox.push(receiveMsg);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
