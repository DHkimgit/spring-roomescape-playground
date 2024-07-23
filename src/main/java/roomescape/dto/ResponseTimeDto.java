package roomescape.dto;

public class ResponseTimeDto {

    private Long id;
    private String time;

    public ResponseTimeDto(final Long id, final String time) {
        this.id = id;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}
