package com.fiap.tech_challenge.core.exception.factory;


import com.fiap.tech_challenge.core.dto.address.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

public class ExceptionDetailsFactory {

    private ExceptionDetailsFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ErrorResponseDto createProblem(String type, HttpStatus status, String detail, WebRequest request) {
        return new ErrorResponseDto(
                type,
                status.getReasonPhrase(),
                status.value(),
                detail,
                request.getDescription(false).replace("uri=", "")
        );
    }
}
