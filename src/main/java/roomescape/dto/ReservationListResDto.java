package roomescape.dto;

import roomescape.domain.Reservation;
import roomescape.domain.Time;

public record ReservationListResDto(
        Long id,
        String name,
        String date,
        Time time
) {
    public static ReservationListResDto from(Reservation reservation) {
        return new ReservationListResDto(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                reservation.getTime()
        );
    }
}
