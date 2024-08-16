package roomescape.dto;

import jakarta.validation.constraints.NotBlank;

public class ReservationCreateRequestDto {

    @NotBlank
    private final String name;
    @NotBlank
    private final String date;
    @NotBlank
    private final String time;

    public ReservationCreateRequestDto(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}