package roomescape.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import roomescape.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationResponseDto {
    private final Long id;
    private final String name;
    private final LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    private final LocalTime time;

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

    public ReservationResponseDto(Reservation entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.date = entity.getDate();
        this.time = entity.getTime();
    }
}
