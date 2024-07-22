package roomescape.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeTest {
    @Test
    @DisplayName("필드가 하나라도 null이면 IllegalArgumentException 발생")
    void shouldThrowExceptionWhenFieldIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Time(null, "10:00"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, null));
    }

    @Test
    @DisplayName("시간 형식이 맞지 않으면 IllegalArgumentException 발생")
    void shouldThrowExceptionWhenTimeFormatIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, ""));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "1"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "10"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "1시"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "10시"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "1시1분"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "10시10분"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "1:1"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "1:00"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "25:00"));
        assertThrows(IllegalArgumentException.class, () -> new Time(1L, "10:60"));
    }

    @Test
    @DisplayName("모든 필드가 정상적으로 입력되면 Time 객체 생성")
    void shouldCreateTimeInstanceWhenFieldsAreValid() {
        Time time = assertDoesNotThrow(
                () -> new Time(1L, "10:00")
        );
        assertEquals(1L, time.getId());
        assertEquals("10:00", time.getTimeValue());
    }
}
