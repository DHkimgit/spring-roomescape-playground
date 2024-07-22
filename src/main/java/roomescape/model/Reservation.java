package roomescape.model;

public class Reservation {
    private final Long id;
    private final String name;
    private final String date;
    private final Time time;

    public Reservation(Long id, String name, String date, Time time) {
        checkAllFields(id, name, date, time);
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    private void checkAllFields(Long id, String name, String date, Time time) {
        checkId(id);
        checkName(name);
        checkDate(date);
        checkTime(time);
    }

    private void checkId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is required");
        }
    }

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

    private void checkTime(Time time) {
        if (time == null) {
            throw new IllegalArgumentException("Time is required");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
