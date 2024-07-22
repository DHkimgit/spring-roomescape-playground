package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.dao.TimeDAO;
import roomescape.dto.ResponseTime;
import roomescape.model.Time;

import java.util.List;

@Service
public class TimeService {
    private final TimeDAO timeDAO;

    public TimeService(TimeDAO timeDAO) {
        this.timeDAO = timeDAO;
    }

    public List<ResponseTime> getTimes() {
        List<Time> times = timeDAO.findAll();
        return times.stream()
                .map(this::mapToResponseTime)
                .toList();
    }

    public ResponseTime addTime(String time) {
        Time newTime = timeDAO.save(time);
        return mapToResponseTime(newTime);
    }

    public void deleteTime(Long id) {
        timeDAO.deleteById(id);
    }

    private ResponseTime mapToResponseTime(Time time) {
        long id = time.getId();
        String timeValue = time.getTimeValue();
        return ResponseTime.of(id, timeValue);
    }
}
