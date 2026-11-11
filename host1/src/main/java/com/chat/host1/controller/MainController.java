package com.chat.host1.controller;

import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.host1.data.ChatBox;
import com.chat.host1.mythread.PrintThread;
import com.chat.host1.mythread.ReceiveThread;
import com.chat.host1.mythread.SendThread;
import com.chat.host1.service.myRestTemplateService;


@RestController 
@RequestMapping("/host1")
public class MainController {

    private myRestTemplateService restTemplateService;
    private ChatBox sendBox;
    private ChatBox receiveBox;
    private Scanner scanner;
    
    public MainController(myRestTemplateService restTemplateService){
        this.restTemplateService = restTemplateService;
        this.sendBox = new ChatBox();
        this.receiveBox = new ChatBox();
        this.scanner = new Scanner(System.in);
    }

    @GetMapping("/request-connection")
    public void preRequestConnection() {
        String input;

        System.out.println("세션을 연결하시겠습니까? y/n");
        input = scanner.nextLine();
        if(input.equals("y") || input.equals("yes"))
            if(requestConnection()) System.out.println("세션에 연결되었습니다. 채팅을 시작하세요.\n");
            else System.out.println("세션 연결이 거부되었습니다.\n");
        else System.out.println("프로그램을 종료합니다.\n");
    }
    
    public boolean requestConnection(){

        PrintThread printThread = new PrintThread(receiveBox);
        SendThread sendThread = new SendThread(sendBox);
        ReceiveThread receiveThread = new ReceiveThread(restTemplateService, receiveBox);
        
        if(restTemplateService.requestConnection()){
            sendThread.start();
            receiveThread.start();
            printThread.start();
            return true;
        }
        return false;
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
