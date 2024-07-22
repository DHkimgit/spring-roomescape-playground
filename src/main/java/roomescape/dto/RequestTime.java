package roomescape.dto;

public record RequestTime(String time) {
    private static final String TIME_REGEX = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

    private void checkTime(String time) {
        if (time == null || !time.matches(TIME_REGEX)) {
            throw new IllegalArgumentException("time format is not correct");
        }
    }

    public RequestTime {
        checkTime(time);
    }
}