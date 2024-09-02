package roomescape.dto;

import roomescape.domain.Reservation;
import roomescape.exception.RequestMissingArgumentException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public record ReservationRequestDto (
        LocalDate date,
        String name,
        LocalTime time
) {
    // compact 생성자에서 데이터 검증
    public ReservationRequestDto {
        Objects.requireNonNull(date);
        Objects.requireNonNull(name);
        if (name.trim().isEmpty()) {
            throw new RequestMissingArgumentException();
        }
        Objects.requireNonNull(time);
    }

    public Reservation toEntity(Long id) {
        return new Reservation(
                id,
                this.name,
                this.date.toString(),
                this.time.toString()
        );
    }
}
