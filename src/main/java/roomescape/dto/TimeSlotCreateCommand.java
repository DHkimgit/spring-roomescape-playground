package roomescape.dto;

import java.time.LocalTime;

public record TimeSlotCreateCommand(
    LocalTime time
) {
}
