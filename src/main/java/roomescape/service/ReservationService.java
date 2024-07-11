package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.dao.RoomDAO;
import roomescape.domain.Reservation;
import roomescape.domain.Time;
import roomescape.dto.ReservationDto;

@Service
public class ReservationService {
    private final RoomDAO roomDAO;
    private final TimeService timeService;

    public ReservationService(RoomDAO roomDAO, TimeService timeService) {
        this.roomDAO = roomDAO;
        this.timeService = timeService;
    }

    public Reservation addReservation(ReservationDto reservationDto) {
        String name = reservationDto.name();
        String date = reservationDto.date();
        String timeString = reservationDto.time().getTime();

        Time time = timeService.findByTime(timeString);
        if (time == null) {
            throw new IllegalArgumentException("시간을 찾을 수 없습니다.");
        }

        Reservation reservation = new Reservation(0, name, date, time);
        roomDAO.insert(reservation);
        int id = roomDAO.getId(reservation);
        reservation.setId(id);
        return reservation;
    }
}
