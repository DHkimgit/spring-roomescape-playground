package roomescape.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Void> handleReservationNotFound(ReservationNotFoundException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
    }

    @ExceptionHandler(RequestMissingArgumentException.class)
    public ResponseEntity<Void> handleRequestMissingArgument(RequestMissingArgumentException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
    }
}
