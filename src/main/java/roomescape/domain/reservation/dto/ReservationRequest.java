package roomescape.domain.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.springframework.format.annotation.DateTimeFormat;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.time.ReservationTime;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    @NotBlank
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    private Long timeId;

    public Reservation toReservation() {
        return new Reservation(null, name, date, timeId, null);
    }
}
