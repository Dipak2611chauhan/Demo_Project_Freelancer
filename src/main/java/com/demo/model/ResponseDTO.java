package com.demo.model;

import org.springframework.http.HttpStatus;

public class ResponseDTO {
    HttpStatus httpStatus;
    String message;
    String data;

    public ResponseDTO() {
    }

    public ResponseDTO(HttpStatus httpStatus, String message, String data) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
