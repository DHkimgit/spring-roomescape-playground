package roomescape.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleException(CustomException e) {
        log.error("CustomException {}", e.getMessage());

        ErrorCode errorCode = e.getErrorCode();
        ResponseErrorDto errorDto = new ResponseErrorDto(errorCode.getHttpStatus().value(), errorCode.getMessage(), e.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity handleNumberFormatException(NumberFormatException e) {
        log.error("NumberFormatException {}", e.getMessage());

        String errorMessage = "숫자가 아닌 문자를 반환하고 있습니다! 확인해주세요.";
        ResponseErrorDto errorDto = new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(), errorMessage, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleSqlException(SQLException e) {
        log.error("SQLException {}", e.getMessage());

        String errorMessage = "잘못된 요청입니다! 확인해주세요";
        ResponseErrorDto errorDto = new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(), errorMessage, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
