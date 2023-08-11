package com.hellomaker.web.exception.instance;


public class NotLoginException extends Exception{

    public NotLoginException(){
        super();
    }
    public NotLoginException(String message) {
        super(message);
    }

}
