package roomescape.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import roomescape.dao.TimeDAO;
import roomescape.dto.ResponseTime;
import roomescape.model.Time;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeServiceTest {
    @Mock
    private TimeDAO timeDAO;

    @InjectMocks
    private TimeService timeService;

    @Test
    @DisplayName("시간 목록 조회 테스트")
    void getTimesTest() {
        // given
        Time time1 = new Time(1L, "10:00");
        Time time2 = new Time(2L, "12:00");
        when(timeDAO.findAll()).thenReturn(List.of(time1, time2));

        // when
        List<ResponseTime> times = timeService.getTimes();

        // then
        assertEquals(2, times.size());
        assertEquals(1L, times.get(0).id());
        assertEquals(2L, times.get(1).id());
        assertEquals("10:00", times.get(0).time());
        assertEquals("12:00", times.get(1).time());
    }

    @Test
    @DisplayName("시간 추가 테스트")
    void addTimeTest() {
        // given
        Time time = new Time(1L, "10:00");
        when(timeDAO.save("10:00")).thenReturn(time);

        // when
        ResponseTime responseTime = timeService.addTime("10:00");

        // then
        assertEquals(1L, responseTime.id());
        assertEquals("10:00", responseTime.time());
    }

    @Test
    @DisplayName("시간 삭제 테스트")
    void deleteTimeTest() {
        // given
        Long id = 1L;

        // when
        timeService.deleteTime(id);

        // then
        verify(timeDAO).deleteById(id);
    }
}
