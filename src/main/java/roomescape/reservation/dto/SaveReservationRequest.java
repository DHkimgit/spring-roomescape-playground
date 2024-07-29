package roomescape.reservation.dto;

import java.util.Arrays;

public record SaveReservationRequest(String name, String date, Long timeId) {
    public SaveReservationRequest {
        validate(name, date, timeId);
    }

    private void validate(String name, String date, Long timeId) {
        validateBlanks(name, date);
    }

    private void validateBlanks(String... fields) {
        boolean isBlankFieldExist = Arrays.stream(fields)
                .anyMatch(String::isBlank);
        if (isBlankFieldExist) {
            throw new RuntimeException("빈 값이 입력되었습니다.");
        }
    }
}
