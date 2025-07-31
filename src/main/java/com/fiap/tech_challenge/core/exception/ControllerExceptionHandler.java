package com.fiap.tech_challenge.core.exception;

import com.fiap.tech_challenge.core.dto.address.ErrorResponseDto;
import com.fiap.tech_challenge.core.exception.factory.ExceptionDetailsFactory;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AlreadyRegisteredException.class, PropertyReferenceException.class, InvalidUserTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleAlreadyRegistered(RuntimeException e, WebRequest request) {
        return ExceptionDetailsFactory.createProblem(e.getClass().toString(), HttpStatus.BAD_REQUEST, e.getMessage(), request);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNotFound(RuntimeException e, WebRequest request) {
        return ExceptionDetailsFactory.createProblem(e.getClass().toString(), HttpStatus.NOT_FOUND, e.getMessage(), request);
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDto handleLoginFailed(LoginFailedException e, WebRequest request) {
        return ExceptionDetailsFactory.createProblem(e.getClass().toString(), HttpStatus.UNAUTHORIZED, e.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleGenericException(Exception e, WebRequest request) {
        return ExceptionDetailsFactory.createProblem(e.getClass().toString(), HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred.", request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .findFirst()
                .orElse("Invalid request content.");

        ErrorResponseDto error = ExceptionDetailsFactory.createProblem(ex.getClass().toString(), HttpStatus.BAD_REQUEST,
                errorMessage, request);

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }
}