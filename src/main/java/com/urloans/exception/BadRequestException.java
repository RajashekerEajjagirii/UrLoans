package com.urloans.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
