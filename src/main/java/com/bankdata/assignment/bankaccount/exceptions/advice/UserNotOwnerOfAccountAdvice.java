package com.bankdata.assignment.bankaccount.exceptions.advice;

import com.bankdata.assignment.bankaccount.exceptions.UserNotOwnerOfAccountException;
import com.bankdata.assignment.bankaccount.exceptions.UsernameOrPasswordWrongException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotOwnerOfAccountAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotOwnerOfAccountException.class)
    @ResponseStatus(HttpStatus.OK)
    String userNotOwnerOfAccountHandler(UserNotOwnerOfAccountException exception) {
        return exception.getMessage();
    }
}
