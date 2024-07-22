package roomescape.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import roomescape.dao.ReservationDAO;
import roomescape.dao.TimeDAO;
import roomescape.dto.ResponseReservation;
import roomescape.model.Reservation;
import roomescape.model.Time;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @Mock
    private ReservationDAO reservationDAO;

    @Mock
    private TimeDAO timeDAO;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("예약 목록 조회 테스트")
    void getReservationsTest() {
        // given
        Time time1 = new Time(1L, "10:00");
        Time time2 = new Time(2L, "12:00");
        Reservation reservation1 = new Reservation(1L, "bang", "2024-07-11", time1);
        Reservation reservation2 = new Reservation(2L, "bang", "2024-07-12", time2);
        when(reservationDAO.findAll()).thenReturn(List.of(reservation1, reservation2));

        // when
        List<ResponseReservation> reservations = reservationService.getReservations();

        // then
        assertEquals(2, reservations.size());
        assertEquals(1L, reservations.get(0).id());
        assertEquals(2L, reservations.get(1).id());
        assertEquals("bang", reservations.get(0).name());
        assertEquals("bang", reservations.get(1).name());
        assertEquals("2024-07-11", reservations.get(0).date());
        assertEquals("2024-07-12", reservations.get(1).date());
        assertEquals(1L, reservations.get(0).time().id());
        assertEquals(2L, reservations.get(1).time().id());
        assertEquals("10:00", reservations.get(0).time().time());
        assertEquals("12:00", reservations.get(1).time().time());
    }

    @Test
    @DisplayName("예약 추가 테스트")
    void createReservationTest() {
        // given
        Time time = new Time(1L, "10:00");
        Reservation reservation = new Reservation(1L, "bang", "2024-07-11", time);
        when(reservationDAO.save("bang", "2024-07-11", 1L)).thenReturn(reservation);

        // when
        ResponseReservation responseReservation = reservationService.createReservation("bang", "2024-07-11", 1L);

        // then
        assertEquals(1L, responseReservation.id());
        assertEquals("bang", responseReservation.name());
        assertEquals("2024-07-11", responseReservation.date());
        assertEquals(1L, responseReservation.time().id());
        assertEquals("10:00", responseReservation.time().time());
    }

    @Test
    @DisplayName("예약 삭제 테스트")
    void deleteReservationTest() {
        // given
        long id = 1L;

        // when
        reservationService.deleteReservation(id);

        // then
        verify(reservationDAO).deleteById(id);
    }
}
