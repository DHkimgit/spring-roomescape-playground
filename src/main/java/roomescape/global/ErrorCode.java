package roomescape.global;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
  
    RESERVATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "예약을 찾을 수 없습니다."),
    TIME_NOT_FOUND(HttpStatus.BAD_REQUEST, "시간을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(final HttpStatus httpStatus, final String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}