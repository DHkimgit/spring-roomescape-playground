package roomescape.exception;

public class TimeSlotNotFoundException extends RuntimeException{
    public TimeSlotNotFoundException() {
        super();
    }

    public TimeSlotNotFoundException(String message) {
        super(message);
    }
}
