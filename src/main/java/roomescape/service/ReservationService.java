package roomescape.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.dao.ReservationDao;
import roomescape.dao.TimeDao;
import roomescape.domain.Reservation;
import roomescape.domain.Time;
import roomescape.dto.ReservationListResDto;
import roomescape.dto.ReservationReqDto;
import roomescape.exception.ErrorCode;
import roomescape.exception.NotFoundReservationException;

import java.util.List;

@Service
@Transactional
public class ReservationService {
    private final ReservationDao reservationDao;
    private final TimeDao timeDao;

    public ReservationService(ReservationDao reservationDao, TimeDao timeDao) {
        this.reservationDao = reservationDao;
        this.timeDao = timeDao;
    }
    @Transactional(readOnly = true)
    public List<ReservationListResDto> findAllReservations() {
        List<Reservation> reservations = reservationDao.findAll();
        return reservations.stream()
                .map(ReservationListResDto::from)
                .toList();
    }

    public Reservation createReservation(ReservationReqDto reservationReqDto) {
        Time time = timeDao.findById(reservationReqDto.timeId());
        Reservation reservation = new Reservation(null, reservationReqDto.name(), reservationReqDto.date(), time);
        return reservationDao.insertWithKeyHolder(reservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationDao.findById(id);
        if (reservation != null) {
            reservationDao.deleteId(id);
        }
        throw new NotFoundReservationException(ErrorCode.RESERVATION_NOT_FOUND);
    }
}
