package roomescape.domain.time;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ReservationTime {
    private Long id;
    private LocalTime time;

    public ReservationTime() {}

    public ReservationTime(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public ReservationTime(LocalTime time) {
        this.time = time;
    }
}
