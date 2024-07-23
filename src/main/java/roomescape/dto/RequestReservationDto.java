package roomescape.dto;

import jakarta.validation.constraints.NotBlank;

public class RequestReservationDto {

    @NotBlank
    private String date;
    @NotBlank
    private String name;
    @NotBlank
    private String time;

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
