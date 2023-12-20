package com.ohgiraffers.exception;

public class MemeberRegistException extends Exception{
    public MemeberRegistException(String message) {
        super(message); //전달한 메세지가 부모에게 전달됨
    }
}
