package roomescape.dto;

import java.time.LocalTime;
import java.util.Objects;

public record TimeSlotCreateRequest (
    LocalTime time
) {
    public TimeSlotCreateRequest {
        Objects.requireNonNull(time);
    }
}
