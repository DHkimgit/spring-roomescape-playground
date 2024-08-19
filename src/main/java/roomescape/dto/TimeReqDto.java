package roomescape.dto;

import jakarta.validation.constraints.NotEmpty;

public record TimeReqDto(
        @NotEmpty(message = "시간을 선택해주세요")
        String time
) {
}
