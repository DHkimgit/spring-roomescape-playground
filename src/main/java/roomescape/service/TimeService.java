package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.dao.TimeDAO;
import roomescape.domain.Time;
import roomescape.dto.TimeDto;

import java.util.List;

@Service
public class TimeService {
    private final TimeDAO timeDAO;

    public TimeService(TimeDAO timeDAO) {
        this.timeDAO = timeDAO;
    }

    public Time addTime(TimeDto timeDto) {
        Time time = new Time(0, timeDto.time());
        timeDAO.insert(time);
        int id = timeDAO.getId(time);
        time.setId(id);
        return time;
    }

    public List<Time> getAllTimes() {
        return timeDAO.findAll();
    }

    public void deleteTime(int id) {
        timeDAO.delete(id);
    }

    public Time findByTime(String time) {
        return timeDAO.findByTime(time);
    }
}
