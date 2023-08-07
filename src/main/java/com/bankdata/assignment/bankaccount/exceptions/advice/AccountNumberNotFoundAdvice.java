package com.bankdata.assignment.bankaccount.exceptions.advice;

import com.bankdata.assignment.bankaccount.exceptions.AccountNotFoundException;
import com.bankdata.assignment.bankaccount.exceptions.AccountNumberNotFoundException;
import com.bankdata.assignment.bankaccount.exceptions.UsernameOrPasswordWrongException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountNumberNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AccountNumberNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    String accountNumberNotFoundHandler(AccountNumberNotFoundException exception) {
        return exception.getMessage();
    }
}
