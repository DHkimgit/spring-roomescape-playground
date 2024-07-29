package roomescape.domain.time.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Data
public class TimeRequest {
    @NotNull(message = "Time must not be null")
    private LocalTime time;

    public TimeRequest() {}

    public TimeRequest(LocalTime time) {
        this.time = time;
    }
}
