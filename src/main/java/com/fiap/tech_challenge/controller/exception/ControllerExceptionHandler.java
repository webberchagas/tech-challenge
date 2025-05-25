package com.fiap.tech_challenge.controller.exception;

import com.fiap.tech_challenge.controller.dto.ErrorResponseDto;
import com.fiap.tech_challenge.controller.exception.factory.ExceptionDetailsFactory;
import com.fiap.tech_challenge.exceptions.NotFoundException;
import com.fiap.tech_challenge.exceptions.UserAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ErrorResponseDto handleAlreadyRegistered(UserAlreadyRegisteredException e, WebRequest request) {
        return ExceptionDetailsFactory.createProblem(e.getClass().toString(), HttpStatus.BAD_REQUEST, e.getMessage(), request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDto handleNotFound(RuntimeException e, WebRequest request) {
        return ExceptionDetailsFactory.createProblem(e.getClass().toString(), HttpStatus.NOT_FOUND, e.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseDto handleGenericException(Exception e, WebRequest request) {
        return ExceptionDetailsFactory.createProblem(
                e.getClass().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred.",
                request
        );
    }
}