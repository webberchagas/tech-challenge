package com.fiap.tech_challenge.controller.exception;

import com.fiap.tech_challenge.controller.dto.ErrorResponseDto;
import com.fiap.tech_challenge.exceptions.UserAlreadyRegisteredException;
import com.fiap.tech_challenge.exceptions.UserNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponseDto userAlreadyRegisteredHandleException(UserAlreadyRegisteredException e) {
        return new ErrorResponseDto(e.getMessage(), BAD_REQUEST.name());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponseDto notReadableHandleException(HttpMessageNotReadableException e) {
        return new ErrorResponseDto(e.getMessage(), BAD_REQUEST.name());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponseDto methodArgumentNotValidHandleException(MethodArgumentNotValidException e) {
        return new ErrorResponseDto(e.getMessage(), BAD_REQUEST.name());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponseDto userNotFoundHandleException(UserNotFoundException e) {
        return new ErrorResponseDto(e.getMessage(), BAD_REQUEST.name());
    }

}
