package dev.anto.gestore.catalogo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDto> handleException(ResponseStatusException exc){
        ErrorDto error = new ErrorDto();
        error.setStatus(exc.getStatusCode().value());
        error.setMessage(exc.getReason());

        return new ResponseEntity<>(error, exc.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(Exception exc) {
        ErrorDto error = new ErrorDto();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
