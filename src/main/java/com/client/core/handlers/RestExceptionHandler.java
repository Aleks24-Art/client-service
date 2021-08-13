package com.client.core.handlers;

import com.client.core.exception.ClientLoginAlreadyRegisteredException;
import com.client.core.exception.ClientNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            ConstraintViolationException.class
    })
    public ApiError handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(v -> v.getRootBeanClass().getName() + " " + v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.toList());
        ApiError result = ApiErrorFactory.badRequest(request, "A constraint violation has occurred", errors);
        log.error("Constraint violation. Response: {}", result);
        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            ClientLoginAlreadyRegisteredException.class,
    })
    public ApiError handlClientLoginAlreadyExist(ClientLoginAlreadyRegisteredException ex, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError result = ApiErrorFactory.badRequest(request, ex.getMessage(), errors);
        log.error("Constraint violation. Response: {}", result);
        return result;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {
            ClientNotFoundException.class
    })
    public ApiError handleNotFoundException(RuntimeException ex, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError result = ApiErrorFactory.notFound(request, ex.getMessage(), errors);
        log.error("Entity not found exception. Response: {}", result);
        return result;
    }
}
