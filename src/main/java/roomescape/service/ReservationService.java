package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDAO;
import roomescape.dto.ResponseReservation;
import roomescape.dto.ResponseTime;
import roomescape.model.Reservation;
import roomescape.model.Time;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public List<ResponseReservation> getReservations() {
        List<Reservation> reservations = reservationDAO.findAll();
        return reservations.stream()
                .map(this::mapToResponseReservation)
                .toList();
    }

    public ResponseReservation createReservation(String name, String date, Long timeId) {
        Reservation savedReservation = reservationDAO.save(name, date, timeId);
        return mapToResponseReservation(savedReservation);
    }

    public void deleteReservation(Long id) {
        reservationDAO.deleteById(id);
    }

    private ResponseReservation mapToResponseReservation(Reservation reservation) {
        long id = reservation.getId();
        String name = reservation.getName();
        String date = reservation.getDate();
        Time time = reservation.getTime();
        return ResponseReservation.of(id, name, date, mapToResponseTime(time));
    }

    private ResponseTime mapToResponseTime(Time time) {
        long id = time.getId();
        String timeValue = time.getTimeValue();
        return ResponseTime.of(id, timeValue);
    }
}
