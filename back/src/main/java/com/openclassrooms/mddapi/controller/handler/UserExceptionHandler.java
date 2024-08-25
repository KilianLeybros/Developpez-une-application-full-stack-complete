package com.openclassrooms.mddapi.controller.handler;

import com.openclassrooms.mddapi.controller.UserController;
import com.openclassrooms.mddapi.controller.exception.BadPasswordException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(assignableTypes = {UserController.class})
@Hidden
public class UserExceptionHandler {

    @ExceptionHandler(BadPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(BadPasswordException exception){
        return exception.getMessage();
    }

}
