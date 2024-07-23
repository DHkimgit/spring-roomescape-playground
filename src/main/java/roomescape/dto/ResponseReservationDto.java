package roomescape.dto;

public class ResponseReservationDto {

    private Long id;
    private String name;
    private String date;
    private ResponseTimeDto time;

    public ResponseReservationDto(final Long id, final String name, final String date, final ResponseTimeDto time) {
        this.id = id;
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

    public ResponseTimeDto getTime() {
        return time;
    }
}