package com.chat.host2.controller;

import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.host2.data.ChatBox;
import com.chat.host2.mythread.PrintThread;
import com.chat.host2.mythread.ReceiveThread;
import com.chat.host2.mythread.SendThread;
import com.chat.host2.service.RestTemplateService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController 
@RequestMapping("/host2")
public class RestTemplateController {

    private RestTemplateService restTemplateService;
    private Scanner scanner;
    private ChatBox sendBox;
    private ChatBox receiveBox;
    
    public RestTemplateController(RestTemplateService restTemplateService){
        this.restTemplateService = restTemplateService;
        this.scanner = new Scanner(System.in);
        this.sendBox = new ChatBox();
        this.receiveBox = new ChatBox();
    }

    @GetMapping("/response-connection")
    public boolean preResponseConnection() {
        System.out.println("연결 요청이 왔습니다. 연결하시겠습니까? y/n");
        String input = scanner.nextLine();
        if(input.equals("y") || input.equals("yes")){
            responseConnection();
            System.out.println("연결을 승인하셨습니다. 채팅을 시작하세요.\n");
            return true;
        }
        System.out.println("연결을 거부하셨습니다.\n");
        return false;
    }

    public void responseConnection() {        
        PrintThread printThread = new PrintThread(receiveBox);
        SendThread sendThread = new SendThread(sendBox);
        ReceiveThread receiveThread = new ReceiveThread(restTemplateService, receiveBox);
        
        sendThread.start();
        receiveThread.start();
        printThread.start();
    }

    @GetMapping("/response-msg")
    public String responseMSG(){
        if(!sendBox.isEmpty())
            return sendBox.pop();
        return "null";
    }
    
}
