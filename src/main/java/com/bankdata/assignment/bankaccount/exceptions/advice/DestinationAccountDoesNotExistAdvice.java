package com.bankdata.assignment.bankaccount.exceptions.advice;

import com.bankdata.assignment.bankaccount.exceptions.DestinationAccountDoesNotExistException;
import com.bankdata.assignment.bankaccount.exceptions.UsernameOrPasswordWrongException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DestinationAccountDoesNotExistAdvice {

    @ResponseBody
    @ExceptionHandler(DestinationAccountDoesNotExistException.class)
    @ResponseStatus(HttpStatus.OK)
    String destinationAccountDoesNotExsitsHandler(DestinationAccountDoesNotExistException exception) {
        return exception.getMessage();
    }
}
