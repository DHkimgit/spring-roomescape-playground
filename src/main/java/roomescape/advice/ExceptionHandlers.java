package roomescape.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import roomescape.exception.IdNotExistException;
import roomescape.exception.TimeNotExistException;

@ControllerAdvice("roomescape.controller")
public class ExceptionHandlers {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IdNotExistException.class)
    public ResponseEntity<String> idNotExistExceptionHandler(IdNotExistException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(TimeNotExistException.class)
    public ResponseEntity<String> timeNotExistExceptionHandler(TimeNotExistException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}