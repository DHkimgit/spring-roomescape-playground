package roomescape.domain.time.dto;

import lombok.Data;
import roomescape.domain.time.ReservationTime;

import java.time.LocalTime;

@Data
public class TimeResponse {
    private Long id;
    private LocalTime time;

    public TimeResponse() {}

    public TimeResponse(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public static TimeResponse from(ReservationTime time) {
        return new TimeResponse(time.getId(), time.getTime());
    }
}
