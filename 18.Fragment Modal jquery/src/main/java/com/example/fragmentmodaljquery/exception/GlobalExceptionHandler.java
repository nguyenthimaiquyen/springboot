package com.example.fragmentmodaljquery.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({SubjectNotFoundException.class})
    public void handleSubjectNotFoundException(SubjectNotFoundException exception) {
        log.error(exception.getMessage(), exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({StudentNotFoundException.class})
    public void handleStudentNotFoundException(StudentNotFoundException exception) {
        log.error(exception.getMessage(), exception);
    }
}
