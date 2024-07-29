package roomescape.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.time.ReservationTime;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long id;
    private String name;
    private LocalDate date;
    private Long timeId;
    private LocalTime timeValue;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.name = reservation.getName();
        this.date = reservation.getDate();
        this.timeId = reservation.getTimeId();
        this.timeValue = reservation.getTime() != null ? reservation.getTime().toLocalTime() : null;
    }

    public ReservationResponse(Reservation reservation, ReservationTime reservationTime) {
        this(reservation);
        if (reservationTime != null) {
            this.timeId = reservationTime.getId();
            this.timeValue = reservationTime.getTime();
        }
    }

    public static ReservationResponse fromReservation(Reservation reservation) {
        return new ReservationResponse(reservation);
    }

    public static ReservationResponse fromReservation(Reservation reservation, ReservationTime reservationTime) {
        return new ReservationResponse(reservation, reservationTime);
    }
}
