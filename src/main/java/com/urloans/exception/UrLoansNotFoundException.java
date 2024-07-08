package com.urloans.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UrLoansNotFoundException extends RuntimeException {

    public UrLoansNotFoundException(String message) {
        super(message);

    }
}
