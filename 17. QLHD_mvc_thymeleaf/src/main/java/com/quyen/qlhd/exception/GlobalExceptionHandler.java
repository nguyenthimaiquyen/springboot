package com.quyen.qlhd.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ServiceNotFoundException.class})
    public void handleServiceNotFoundException(ServiceNotFoundException exception) {
        log.error(exception.getMessage(), exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({CustomerNotFoundException.class})
    public void handleCustomerNotFoundException(CustomerNotFoundException exception) {
        log.error(exception.getMessage(), exception);
    }
}
