package com.urloans.exception;

import com.urloans.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.resource.transaction.backend.jta.internal.synchronization.ExceptionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//@Component
@Slf4j
@RestController
@ControllerAdvice
public class UrLoansExceptionHandler{


    @ExceptionHandler(value = UrLoansNotFoundException.class)
    public  ResponseEntity<ExceptionResponse> notFoundException(UrLoansNotFoundException ex){
//        ExceptionResponse error=new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "User was not Found",new Date());
            ExceptionResponse error=new ExceptionResponse();
            error.setStatusCode(HttpStatus.NOT_FOUND.value());
            error.setErrorMessage(ex.getMessage());
            error.setDate(new Date());
            log.error(error.toString());

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ExceptionResponse> badRequest(BadRequestException ex){
        log.info("Comming to BadRequest Handler");
        ExceptionResponse error=new ExceptionResponse();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(ex.getMessage());
        error.setDate(new Date());
        log.error(error.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
