package com.bankdata.assignment.bankaccount.exceptions.advice;


import com.bankdata.assignment.bankaccount.exceptions.InsufficientFundsException;
import com.bankdata.assignment.bankaccount.exceptions.UsernameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InsufficientFundsAdvice {

    @ResponseBody
    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.OK)
    String insufficientFundsHandler(InsufficientFundsException exception) {
        return exception.getMessage();
    }
}
