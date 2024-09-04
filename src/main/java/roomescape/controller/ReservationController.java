package roomescape.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequestDto;
import roomescape.dto.ReservationResponseDto;
import roomescape.exception.RequestMissingArgumentException;
import roomescape.exception.ReservationNotFoundException;
import roomescape.service.ReservationService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private List<Reservation> reservations = new ArrayList<>();
    private AtomicLong index = new AtomicLong(0);
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostConstruct
    public void initData() {
        reservations.add(new Reservation(index.incrementAndGet(), "브라운", "2023-01-01", "10:00"));
        reservations.add(new Reservation(index.incrementAndGet(), "브라운", "2023-01-02", "11:00"));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservations() {
        var response = reservationService.getReservations();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(@RequestBody ReservationRequestDto requestDto) {
        Reservation newReservation = requestDto.toEntity(index.incrementAndGet());
        reservations.add(newReservation);
        return ResponseEntity.created(URI.create("/reservations/" + newReservation.getId())).body(ReservationResponseDto.from(newReservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeReservation(@PathVariable Long id) {
        Reservation findReservation = reservations.stream()
                .filter(iter -> Objects.equals(iter.getId(), id))
                .findFirst()
                .orElseThrow(ReservationNotFoundException::new);

        reservations.remove(findReservation);

        return ResponseEntity.noContent().build();
    }
}
