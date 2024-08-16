package roomescape.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.dao.QueryDao;
import roomescape.dto.ReservationCreateRequestDto;
import roomescape.entity.Reservation;
import roomescape.exception.NotFoundReservationException;

@Controller
public class ReservationController {

    private final QueryDao queryDao;

    @Autowired
    public ReservationController(QueryDao queryDao) {
        this.queryDao = queryDao;
    }

    @GetMapping("/reservation")
    public String reservation(Model model) {
        model.addAttribute("reservations", queryDao.getAllReservations());
        return "reservation";
    }

    // 예약 추가
    @PostMapping("/reservations")
    @ResponseBody
    public ResponseEntity<Reservation> addReservation(@RequestBody @Valid ReservationCreateRequestDto request) {
        Reservation reservation = queryDao.insertReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/reservations/"+reservation.getId())).body(reservation);
    }

    // 예약 조회
    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.status(HttpStatus.OK).body(queryDao.getAllReservations());
    }

    // 예약 삭제
    @DeleteMapping("/reservations/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        int isDeleted = queryDao.deleteReservation(id);
        if (isDeleted == 0) {
            throw new NotFoundReservationException("Reservation not found");
        }
        return ResponseEntity.noContent().build();
    }
}