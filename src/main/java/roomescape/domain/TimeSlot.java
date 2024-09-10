package roomescape.domain;

import java.time.LocalTime;

public class TimeSlot {
    private Long id;
    private LocalTime time;

    public TimeSlot() {
    }

    public TimeSlot(Builder builder) {
        this.id = builder.id;
        this.time = builder.time;
    }

    public static class Builder {
        private Long id;
        private LocalTime time;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder time(LocalTime time) {
            this.time = time;
            return this;
        }

        public TimeSlot build() {return new TimeSlot(this);}
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }
}
