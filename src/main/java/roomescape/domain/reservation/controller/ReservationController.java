package roomescape.domain.reservation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.dto.ReservationRequest;
import roomescape.domain.reservation.dto.ReservationResponse;
import roomescape.domain.reservation.service.ReservationService;

import java.net.URI;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservation")
    public String reservation() {
        return "new-reservation";
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> addReservation(@Valid @RequestBody ReservationRequest request) {
        Reservation newReservation = reservationService.addReservation(request);
        ReservationResponse response = new ReservationResponse(newReservation);

        URI location = URI.create(String.format("/reservations/%d", newReservation.getId()));
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations() {
        List<Reservation> reservations = reservationService.getReservations();
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        boolean deleted = reservationService.deleteReservation(id);
        if (!deleted) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}
