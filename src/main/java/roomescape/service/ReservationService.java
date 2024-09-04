package roomescape.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.dto.ReservationResponseDto;
import roomescape.repository.ReservationRepository;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationResponseDto> getReservations() {
        var reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationResponseDto::from)
                .toList();
    }
}
