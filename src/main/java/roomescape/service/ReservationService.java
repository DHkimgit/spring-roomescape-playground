package roomescape.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.exception.ReservationNotFoundException;
import roomescape.repository.ReservationRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationResponse> getReservations() {
        var reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationResponse::from)
                .toList();
    }

    @Transactional
    public Reservation createReservation(ReservationRequest reservationRequest) {
        return reservationRepository.save(
            new Reservation.Builder()
                .name(reservationRequest.name())
                .date(reservationRequest.date())
                .time(reservationRequest.time())
                .build()
        );
    }

    @Transactional
    public void deleteReservation(Long id) {
        Reservation findReservation = reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.delete(findReservation);
    }
}
