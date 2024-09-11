package roomescape.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import roomescape.domain.Reservation;
import roomescape.domain.TimeSlot;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponse(
        Long id,
        String name,
        LocalDate date,
        TimeSlotResponse time
) {

    public static ReservationResponse from(Reservation entity) {
        return new ReservationResponse(
            entity.getId(),
            entity.getName(),
            entity.getDate(),
            TimeSlotResponse.from(entity.getTimeSlot())
        );
    }
}
