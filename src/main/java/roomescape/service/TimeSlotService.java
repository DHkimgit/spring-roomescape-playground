package roomescape.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import roomescape.domain.TimeSlot;
import roomescape.dto.TimeSlotCreateCommand;
import roomescape.exception.TimeSlotNotFoundException;
import roomescape.repository.TimeSlotRepository;

@Service
@Transactional(readOnly = true)
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> getTimeSlots() { return timeSlotRepository.findAll(); }

    @Transactional
    public TimeSlot createTimeSlot(TimeSlotCreateCommand request) {
        return timeSlotRepository.save(
            new TimeSlot.Builder()
                .time(request.time())
                .build()
        );
    }

    @Transactional
    public void deleteTimeSlot(Long id) {
        TimeSlot findTimeSlot = timeSlotRepository.findById(id).orElseThrow(TimeSlotNotFoundException::new);
        timeSlotRepository.delete(findTimeSlot);
    }
}
