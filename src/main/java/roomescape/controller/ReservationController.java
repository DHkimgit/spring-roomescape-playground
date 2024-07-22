package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.exception.InvalidReservationException;
import roomescape.model.Reservation;
import roomescape.respository.ReservationRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    @ResponseBody
    public List<Reservation> getReservations() {
        return reservationRepository.findAllReservations();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Reservation findReservationById(@PathVariable Long id) {
        return reservationRepository.findReservationById(id);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        validateReservation(reservation);

        Reservation newReservation = reservationRepository.insert(reservation);

        return ResponseEntity.created(URI.create("/reservations/" + newReservation.getId())).body(newReservation);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void validateReservation(Reservation reservation) {
        if (reservation.getName() == null || reservation.getName().isEmpty()) {
                throw new InvalidReservationException("name", "이름이 필요합니다.");
        }
        if (reservation.getDate() == null || reservation.getDate().isEmpty()) {
                throw new InvalidReservationException("date", "날짜가 필요합니다.");
        }
        if (reservation.getTime() == null || reservation.getTime().isEmpty()) {
                throw new InvalidReservationException("time", "시간이 필요합니다.");
        }
    }

}
