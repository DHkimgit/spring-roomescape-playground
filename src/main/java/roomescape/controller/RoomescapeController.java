package roomescape.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import roomescape.domain.Time;
import roomescape.domain.Reservation;
import roomescape.dao.RoomDAO;
import roomescape.dto.ReservationDto;
import roomescape.service.ReservationService;
import roomescape.service.TimeService;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RoomescapeController {

    private final RoomDAO RoomDAO;
    private final TimeService timeService;
    private final ReservationService reservationService;

    public RoomescapeController(RoomDAO roomDAO, TimeService timeService, ReservationService reservationService) {
        this.RoomDAO = roomDAO;
        this.timeService = timeService;
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public String reservation() {
        return "new-reservation";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<ReservationDto>> getAllReservations(){
        List<ReservationDto> reservations = RoomDAO.findAll().stream()
                .map(reservation -> new ReservationDto(reservation.getName(), reservation.getDate(), reservation.getTime()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/reservations")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto) {
        try {
            Reservation reservation = reservationService.addReservation(reservationDto);
            URI location = UriComponentsBuilder.fromPath("/reservations/{id}")
                    .buildAndExpand(reservation.getId())
                    .toUri();
            return ResponseEntity.created(location).body(reservation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @DeleteMapping("/reservations/{id}")
    @ResponseBody
    public ResponseEntity<Reservation> deleteReservation(@PathVariable int id){
            if (RoomDAO.findById(id) != null) {
                RoomDAO.delete(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        // 예약이 없는 경우 Exception 발생
        throw new IllegalArgumentException("삭제할 예약이 없습니다.");
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
