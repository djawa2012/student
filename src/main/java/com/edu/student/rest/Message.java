package com.edu.student.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

    private String errorMsg;

    public Message() {
    }

    public Message(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        System.out.println("Get: " + errorMsg);
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        System.out.println("Set: " + errorMsg);
        this.errorMsg = errorMsg;
    }
}
