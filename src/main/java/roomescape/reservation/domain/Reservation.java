package roomescape.reservation.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import roomescape.time.domain.Time;

public class Reservation {
    private Long id;

    @NotNull(message = "name 이 null 입니다.")
    @NotBlank(message = "name 이 빈문자열입니다.")
    private String name;

    @NotNull(message = "date 가 null 입니다.")
    @NotBlank(message = "date rk 빈문자열입니다.")
    private String date;

    private Time time;

    public Reservation() {
    }

    public Reservation(Long id, String name, String date, Time time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Reservation(String name, String date, Time time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static Reservation toEntity(Reservation reservation, Long id) {
        return new Reservation(id, reservation.name, reservation.date, reservation.time);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
