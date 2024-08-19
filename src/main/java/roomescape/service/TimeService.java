package roomescape.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.domain.Time;
import roomescape.dto.TimeReqDto;
import roomescape.dto.TimeResDto;
import roomescape.dao.TimeDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TimeService {
    private final TimeDao timeDao;

    public TimeService(TimeDao timeDao) {
        this.timeDao = timeDao;
    }

    public TimeResDto createTime(TimeReqDto reqDto) {
        Time time = timeDao.createTime(reqDto.time());
        return new TimeResDto(time.getId(), time.getTime());
    }
    @Transactional(readOnly = true)
    public List<TimeResDto> findAllTimes() {
        return timeDao.findAllTimes().stream()
                .map(time -> new TimeResDto(time.getId(), time.getTime()))
                .collect(Collectors.toList());
    }

    public void deleteTime(Long id) {
        timeDao.deleteById(id);
    }

/*    public TimeResDto findById(Long id) {
        Time time = timeDao.findById(id);
        return new TimeResDto(time.getId(), time.getTime());
    }*/
}
