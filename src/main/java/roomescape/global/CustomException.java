package roomescape.global;

public class CustomException extends RuntimeException{

    ErrorCode errorCode;

    public CustomException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}