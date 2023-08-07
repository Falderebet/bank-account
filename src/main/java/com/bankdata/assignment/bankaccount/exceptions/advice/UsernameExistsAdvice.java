package com.bankdata.assignment.bankaccount.exceptions.advice;

import com.bankdata.assignment.bankaccount.exceptions.AccountNotFoundException;
import com.bankdata.assignment.bankaccount.exceptions.UsernameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsernameExistsAdvice {

    @ResponseBody
    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    String usernameExistsHandler(UsernameExistsException exception) {
        return exception.getMessage();
    }
}
