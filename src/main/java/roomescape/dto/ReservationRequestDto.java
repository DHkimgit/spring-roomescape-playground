package roomescape.dto;

import roomescape.domain.Reservation;
import roomescape.exception.RequestMissingArgumentException;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationRequestDto {
    private LocalDate date;
    private String name;
    private LocalTime time;

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public LocalTime getTime() {
        return time;
    }

    public ReservationRequestDto() {
    }

    public ReservationRequestDto(LocalDate date, String name, LocalTime time) {
        this.date = date;
        this.name = name;
        this.time = time;
    }

    public Reservation toEntity(Long id) {
        if(this.name == null || this.name.trim().isEmpty() || this.date == null || this.time == null) {
            throw new RequestMissingArgumentException();
        }

        return new Reservation(id, this.name, this.date.toString(), this.time.toString());
    }
}
