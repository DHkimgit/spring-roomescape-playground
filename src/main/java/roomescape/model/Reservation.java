package roomescape.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Reservation {
    private Long id;

    @NotNull(message = "name 이 null 입니다.")
    @NotBlank(message = "name 이 빈문자열입니다.")
    private String name;

    @NotNull(message = "date 가 null 입니다.")
    @NotBlank(message = "date rk 빈문자열입니다.")
    private String date;

    @NotNull(message = "time 이 null 입니다.")
    @NotBlank(message = "time 이 빈문자열입니다.")
    private String time;

    public Reservation() {
    }

    public Reservation(Long id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Reservation(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public static Reservation toEntity(Reservation reservation, Long id) {
        return new Reservation(id, reservation.name, reservation.date, reservation.time);
    }
}
