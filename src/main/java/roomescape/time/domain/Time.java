package roomescape.time.domain;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class Time implements Serializable {
    private Long id;

    @NotNull(message = "time 이 null 입니다.")
    private String time;

    public Time() {
    }

    public Time(Long id, String time) {
        this.id = id;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}
