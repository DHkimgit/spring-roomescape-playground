package roomescape.dto;

import roomescape.exception.RequestMissingArgumentException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public record ReservationRequest(
        LocalDate date,
        String name,
        LocalTime time
) {

    public ReservationRequest {
        Objects.requireNonNull(date);
        Objects.requireNonNull(name);
        if (name.trim().isEmpty()) {
            throw new RequestMissingArgumentException();
        }
        Objects.requireNonNull(time);
    }
}
