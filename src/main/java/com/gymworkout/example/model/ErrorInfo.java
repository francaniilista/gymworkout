package com.gymworkout.example.model;

/**
 * Created by pfranca on 4/29/2016.
 */
public class ErrorInfo {
    public final String url;
    public final String exception;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.exception = ex.getLocalizedMessage();
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "url='" + url + '\'' +
                ", exception='" + exception + '\'' +
                '}';
    }
}