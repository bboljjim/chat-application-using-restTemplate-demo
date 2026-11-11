package com.chat.host2.data;

import java.util.ArrayList;

public class ChatBox extends ArrayList<String>  {

    public synchronized void push(String msg){
        addLast(msg);
    }

    public synchronized String pop(){
        return removeFirst();
    }
}
