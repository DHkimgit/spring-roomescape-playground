package roomescape.dto;

import jakarta.validation.constraints.NotBlank;

public class RequestTimeDto {

    @NotBlank
    private String time;

    public String getTime() {
        return time;
    }
}
