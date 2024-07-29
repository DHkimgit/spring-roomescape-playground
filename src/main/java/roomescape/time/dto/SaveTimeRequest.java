package roomescape.time.dto;

import roomescape.time.domain.Time;

public record SaveTimeRequest(String time) {
    public Time toEntity() {
        return new Time(null, time);
    }
}
