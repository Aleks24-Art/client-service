package com.client.core.handlers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiErrorFactory {

    public static ApiError create(HttpStatus status, WebRequest request, String message) {
        ApiError error = new ApiError();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(status.value());
        error.setError(status.getReasonPhrase());
        error.setMessage(message);
        error.setPath(getPath(request));
        return error;
    }

    public static ApiError create(HttpStatus status, WebRequest request, String message, List<String> errors) {
        ApiError error = create(status, request, message);
        error.setErrors(errors);
        return error;
    }

    public static ApiError badRequest(WebRequest request, String message, List<String> errors) {
        return create(HttpStatus.BAD_REQUEST, request, message, errors);
    }

    public static ApiError notFound(WebRequest request, String message, List<String> errors) {
        return create(HttpStatus.NOT_FOUND, request, message, errors);
    }

    private static String getPath(WebRequest request) {
        return (request instanceof ServletWebRequest)
                ? ((ServletWebRequest) request).getRequest().getRequestURI()
                : request.getDescription(false).replace("uri=", "");
    }
}
