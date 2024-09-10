package roomescape.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import roomescape.domain.TimeSlot;

public record TimeSlotResponse (
    Long id,
    @JsonFormat(pattern = "HH:mm")
    LocalTime time
){

    public static TimeSlotResponse from(TimeSlot entity) {
        return new TimeSlotResponse(
            entity.getId(),
            entity.getTime()
        );
    }
}
