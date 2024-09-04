package roomescape.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private Long id;
    private String name;
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    public Reservation() {
    }

    public Reservation(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.date = builder.date;
        this.time = builder.time;
    }

    public static class Builder {
        private Long id;
        private String name;
        private LocalDate date;
        private LocalTime time;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder time(LocalTime time) {
            this.time = time;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
