package kz.iitu.itse1909r.mukhitdinov.core.exception;

import org.springframework.http.ResponseEntity;

public class ApiErrorUtils {
    static public ResponseEntity<Object> buildResponseEntity(ApiError error) {
        return new ResponseEntity<>(error, error.getStatus());
    }
}
