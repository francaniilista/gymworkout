package com.gymworkout.example.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by pfranca on 4/28/2016.
 * This exception is thrown if the requested exercise is not found.
 * @author Paulo Franca
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "No such record")
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
