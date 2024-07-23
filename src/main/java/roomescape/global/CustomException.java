package roomescape.global;

public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;

    public CustomException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
