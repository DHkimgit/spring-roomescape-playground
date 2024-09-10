package roomescape.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationCreateCommand;
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

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public Reservation createReservation(ReservationCreateCommand reservationCreateRequest) {
        return reservationRepository.save(
            new Reservation.Builder()
                .name(reservationCreateRequest.name())
                .date(reservationCreateRequest.date())
                .time(reservationCreateRequest.time())
                .build()
        );
    }

    @Transactional
    public void deleteReservation(Long id) {
        Reservation findReservation = reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.delete(findReservation);
    }
}
