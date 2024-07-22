package roomescape.model;

public class Time {
    private static final String TIME_REGEX = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    private final Long id;
    private final String timeValue;

    public Time(Long id, String timeValue) {
        checkId(id);
        checkTime(timeValue);
        this.id = id;
        this.timeValue = timeValue;
    }

    private void checkId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is required");
        }
    }

    private void checkTime(String time) {
        if (time == null || !time.matches(TIME_REGEX)) {
            throw new IllegalArgumentException("time format is not correct");
        }
    }

    public Long getId() {
        return id;
    }

    public String getTimeValue() {
        return timeValue;
    }
}
