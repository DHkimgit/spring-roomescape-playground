package roomescape.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roomescape.domain.Reservation;
import roomescape.dto.RequestReservationDto;
import roomescape.dto.ResponseReservationDto;
import roomescape.dto.ResponseTimeDto;
import roomescape.global.CustomException;
import roomescape.global.ErrorCode;
import roomescape.repository.ReservationRepository;

@Service
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TimeService timeService;

    public ReservationService(final ReservationRepository reservationRepository, final TimeService timeService) {
        this.reservationRepository = reservationRepository;
        this.timeService = timeService;
    }

    @Transactional
    public Reservation createReservation(RequestReservationDto reservationDto) {

        Map<String, String> reservations = new HashMap<>();
        reservations.put("name", reservationDto.getName());
        reservations.put("date", reservationDto.getDate());
        reservations.put("time_id", reservationDto.getTime());

        Reservation reservation = reservationRepository.insert(reservations);
        return reservation;
    }

    public List<ResponseReservationDto> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream()
                .map(reservation -> new ResponseReservationDto(reservation.getId(), reservation.getName(), reservation.getDate(),
                        timeService.findById(reservation.getTime().getId())))
                .toList();

    }

    public ResponseReservationDto findById(Long id) {
        Reservation reservation = reservationRepository.findById(id);
        ResponseTimeDto responseTimeDto = timeService.findById(reservation.getTime().getId());

        return new ResponseReservationDto(reservation.getId(), reservation.getName(), reservation.getDate(),
                responseTimeDto);
    }

    @Transactional
    public void deleteById(Long id) {
        boolean removed = reservationRepository.deleteById(id);
        if (!removed) {
            throw new CustomException(ErrorCode.RESERVATION_NOT_FOUND);
        }
    }
}