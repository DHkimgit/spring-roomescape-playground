package roomescape.exception;

public class RequestMissingArgumentException extends RuntimeException{
    public RequestMissingArgumentException() {
        super();
    }

    public RequestMissingArgumentException(String message) {
        super(message);
    }
}
