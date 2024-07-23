package roomescape.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import roomescape.domain.Reservation;
import roomescape.dto.RequestReservationDto;
import roomescape.dto.ResponseReservationDto;
import roomescape.service.ReservationService;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public String reservation() {
        return "new-reservation";
    }

    @PostMapping("/reservations")
    @ResponseBody
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody RequestReservationDto reservationDto) {

        Reservation reservation = reservationService.createReservation(reservationDto);

        URI location = UriComponentsBuilder.fromPath("/reservations/{id}").buildAndExpand(reservation.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.created(location).body(reservation);
    }

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<ResponseReservationDto>> findAllReservations() {
        List<ResponseReservationDto> reservationDtos = reservationService.findAll();
        return ResponseEntity.ok(reservationDtos);
    }

    @GetMapping("/reservation/{id}")
    @ResponseBody
    public ResponseEntity<ResponseReservationDto> findReservationById(@PathVariable("id") Long id) {
        ResponseReservationDto reservationDtos = reservationService.findById(id);
        return ResponseEntity.ok(reservationDtos);
    }

    @DeleteMapping("/reservations/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteReservationById(@PathVariable("id") Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}