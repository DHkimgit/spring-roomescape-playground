package roomescape.dto;

public record RequestReservation(String name, String date, Long time) {
    private void checkName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }

    private void checkDate(String date) {
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date is required");
        }
    }

    private void checkTimeId(Long timeId) {
        if (timeId == null) {
            throw new IllegalArgumentException("Time is required");
        }
    }

    public RequestReservation {
        checkName(name);
        checkDate(date);
        checkTimeId(time);
    }
}
