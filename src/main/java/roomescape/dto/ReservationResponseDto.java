package roomescape.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import roomescape.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponseDto(
        Long id,
        String name,
        LocalDate date,
        @JsonFormat(pattern = "HH:mm")
        LocalTime time
) {

    public static ReservationResponseDto from(Reservation entity) {
        return new ReservationResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDate(),
                entity.getTime()
        );
    }
}
