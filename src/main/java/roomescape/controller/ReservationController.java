package roomescape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationCreateCommand;
import roomescape.dto.ReservationCreateRequest;
import roomescape.dto.ReservationResponse;
import roomescape.service.ReservationService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getReservations() {
        List<Reservation> reservations = reservationService.getReservations();
        var response = reservations.stream().map(ReservationResponse::from).toList();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationCreateRequest req) {
        Reservation newReservation = reservationService.createReservation(
            new ReservationCreateCommand(req.name(), req.date(), req.time())
        );
        return ResponseEntity.created(URI.create("/reservations/" + newReservation.getId()))
            .body(ReservationResponse.from(newReservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
