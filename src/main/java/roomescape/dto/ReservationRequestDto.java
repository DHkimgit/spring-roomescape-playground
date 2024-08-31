package roomescape.dto;

import roomescape.domain.Reservation;

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
        return new Reservation(id, this.name, this.date.toString(), this.time.toString());
    }
}
