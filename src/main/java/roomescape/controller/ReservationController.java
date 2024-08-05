package roomescape.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Reservation;
import roomescape.exception.NotFoundException;

import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {

    // 예약 목록을 저장할 리스트
    private final List<Reservation> reservations = new ArrayList<>();
    // 예약 ID를 생성하기 위한 원자적(atomic) 카운터
    private AtomicLong index = new AtomicLong(1);

    // 예약 관리 페이지 응답
    @GetMapping("/reservation")
    public String reservation() {
        return "reservation";
    }

    // 예약 추가 페이지 응답
    @GetMapping("/new-reservation")
    public String newReservation() {
        return "new-reservation";
    }

    // 예약 목록 조회 API
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> reservations() {
        return ResponseEntity.ok().body(reservations);
    }

    // 예약 추가 API
    @PostMapping("/reservations/reservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        // 필수 필드 검증
        if (reservation.getName() == null || reservation.getName().isEmpty() ||
                reservation.getDate() == null || reservation.getDate().isEmpty() ||
                reservation.getTime() == null || reservation.getTime().isEmpty()) {
            throw new NotFoundException("Required field is missing");
        }

        // 예약에 ID를 부여하고 목록에 추가
        reservation.setId(index.getAndIncrement());
        reservations.add(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }

    // 예약 삭제 API
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        boolean removed = reservations.removeIf(reservation -> reservation.getId().equals(id));
        if (!removed) {
            throw new NotFoundException("Reservation not found");
        }
        return ResponseEntity.noContent().build();
    }
}
