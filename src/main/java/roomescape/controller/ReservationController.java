package roomescape.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import roomescape.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ReservationController {
    private List<Reservation> reservations = new ArrayList<>();
    private AtomicLong index = new AtomicLong(0);

    @PostConstruct
    public void initData() {
        reservations.add(new Reservation(index.incrementAndGet(), "브라운", LocalDate.of(2023, 1, 1), LocalTime.of(10, 0)));
        reservations.add(new Reservation(index.incrementAndGet(), "브라운", LocalDate.of(2023, 1, 2), LocalTime.of(11, 0)));
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok().body(reservations);
    }
}
