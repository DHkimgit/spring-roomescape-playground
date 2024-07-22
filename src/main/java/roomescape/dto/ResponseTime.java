package roomescape.dto;

public record ResponseTime(
        Long id,
        String time
) {
    public static ResponseTime of(Long id, String time) {
        return new ResponseTime(id, time);
    }
}
