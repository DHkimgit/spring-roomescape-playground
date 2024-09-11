package roomescape.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.domain.Reservation;
import roomescape.domain.TimeSlot;
import roomescape.dto.ReservationCreateCommand;
import roomescape.exception.ReservationNotFoundException;
import roomescape.exception.TimeSlotNotFoundException;
import roomescape.repository.ReservationRepository;
import roomescape.repository.TimeSlotRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public ReservationService(
        ReservationRepository reservationRepository,
        TimeSlotRepository timeSlotRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public Reservation createReservation(ReservationCreateCommand req) {
        TimeSlot timeSlot = timeSlotRepository.findById(req.time())
            .orElseThrow(TimeSlotNotFoundException::new);

        return reservationRepository.save(
            new Reservation.Builder()
                .name(req.name())
                .date(req.date())
                .time(timeSlot)
                .build()
        );
    }

    @Transactional
    public void deleteReservation(Long id) {
        Reservation findReservation = reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.delete(findReservation);
    }
}
