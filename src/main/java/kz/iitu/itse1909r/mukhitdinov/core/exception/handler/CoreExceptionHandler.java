package kz.iitu.itse1909r.mukhitdinov.core.exception.handler;

import kz.iitu.itse1909r.mukhitdinov.core.exception.ApiError;
import kz.iitu.itse1909r.mukhitdinov.core.exception.ApiErrorUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CoreExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleInvalidArgumentException(RuntimeException ex) {
        return ApiErrorUtils.buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }
}

