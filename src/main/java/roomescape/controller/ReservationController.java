package roomescape.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.dto.RequestReservation;
import roomescape.dto.ResponseReservation;
import roomescape.service.ReservationService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public String reservation() {
        return "new-reservation";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<ResponseReservation>> reservations() {
        List<ResponseReservation> reservations = reservationService.getReservations();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(reservations);
    }

    @PostMapping("/reservations")
    @ResponseBody
    public ResponseEntity<ResponseReservation> createReservation(@RequestBody RequestReservation requestReservation) {
        String name = requestReservation.name();
        String date = requestReservation.date();
        long timeId = requestReservation.time();
        ResponseReservation newReservation = reservationService.createReservation(name, date, timeId);

        return ResponseEntity.status(CREATED)
                .header(HttpHeaders.LOCATION, "/reservations/" + newReservation.id())
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(newReservation);
    }

    @DeleteMapping("/reservations/{reservationId}")
    @ResponseBody
    public ResponseEntity<Void> deleteReservations(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }
}
