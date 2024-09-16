package roomescape.repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import roomescape.domain.TimeSlot;

public interface TimeSlotRepository {

    TimeSlot save(TimeSlot timeSlot);

    List<TimeSlot> findAll();

    Optional<TimeSlot> findById(Long id);

    Optional<TimeSlot> findByTime(LocalTime time);

    void delete(TimeSlot timeSlot);
}
