package roomescape.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private Long id;
    private String name;
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    public Reservation() {
    }

    public Reservation(Long id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
