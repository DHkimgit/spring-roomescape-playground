package roomescape.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roomescape.domain.Time;
import roomescape.dto.RequestTimeDto;
import roomescape.dto.ResponseTimeDto;
import roomescape.global.CustomException;
import roomescape.global.ErrorCode;
import roomescape.repository.TimeRepository;

@Service
@Transactional(readOnly = true)
public class TimeService {

    private final TimeRepository timeRepository;

    public TimeService(final TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Transactional
    public Time createTime(RequestTimeDto timeDto) {

        Map<String, String> times = new HashMap<>();
        times.put("time", timeDto.getTime());

        Time time = timeRepository.insert(times);

        return time;
    }

    public List<ResponseTimeDto> findAll() {
        List<Time> times = timeRepository.finaAll();

        return times.stream()
                .map(time -> new ResponseTimeDto(time.getId(), time.getTime()))
                .toList();
    }

    public ResponseTimeDto findById(final Long id) {
        Time time = timeRepository.findById(id);

        return new ResponseTimeDto(time.getId(), time.getTime());
    }

    @Transactional
    public void deleteById(Long id) {
        boolean time = timeRepository.deleteById(id);
        if (!time) {
            throw new CustomException(ErrorCode.TIME_NOT_FOUND);
        }
    }
}
