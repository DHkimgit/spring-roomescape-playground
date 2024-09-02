package roomescape.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservationNotFoundException.class)
    protected final ResponseEntity<Void> handleReservationNotFound(ReservationNotFoundException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
    }

    @ExceptionHandler(RequestMissingArgumentException.class)
    protected final ResponseEntity<Void> handleRequestMissingArgument(RequestMissingArgumentException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
    }

    @ExceptionHandler(NullPointerException.class)
    protected final ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
    }

    @ExceptionHandler(DateTimeParseException.class)
    protected final ResponseEntity<Map<String, String>> handleDateTimeParseException(DateTimeParseException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(response);
    }
}
