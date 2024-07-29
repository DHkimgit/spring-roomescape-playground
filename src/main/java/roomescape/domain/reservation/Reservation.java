package roomescape.domain.reservation;

import lombok.Data;
import roomescape.domain.time.ReservationTime;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Reservation {
    private Long id;
    private String name;
    private LocalDate date;
    private Long timeId;
    private Time time;

    public Reservation() {}

    public Reservation(Long id, String name, LocalDate date, Long timeId, Time time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.timeId = timeId;
        this.time = time;
    }
}
