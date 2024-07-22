package roomescape.dto;

public record ResponseReservation(
        Long id,
        String name,
        String date,
        ResponseTime time
) {
    public static ResponseReservation of(Long id, String name, String date, ResponseTime time) {
        return new ResponseReservation(id, name, date, time);
    }
}
