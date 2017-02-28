package com.gymworkout.example.model.dto.error;

/**
 * Implementation removed from book
 * Spring Rest
 * Chapter 5: Error Handling
 * Authors Balaji Varanasi and Sudha Belida
 * on 8/11/2016.
 */
public class ValidationError {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
