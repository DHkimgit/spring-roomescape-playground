package roomescape.dto;

import jakarta.validation.constraints.NotNull;

public record ReservationReqDto(
        @NotNull(message = "이름을 작성해주세요")
        String name,
        @NotNull(message = "날짜를 작성해주세요")
        String date,
        @NotNull(message = "시간을 선택해주세요")
        Long timeId
){

}
