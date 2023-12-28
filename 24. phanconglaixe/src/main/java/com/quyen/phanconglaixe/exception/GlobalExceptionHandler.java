package com.quyen.phanconglaixe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({DriverNotFoundException.class})
    public void handleDriverNotFoundException(DriverNotFoundException exception) {
        log.error(exception.getMessage(), exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({BusNotFoundException.class})
    public void handleBusNotFoundException(BusNotFoundException exception) {
        log.error(exception.getMessage(), exception);
    }
}
