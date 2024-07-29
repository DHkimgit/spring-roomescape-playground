package roomescape.time.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.time.domain.Time;
import roomescape.time.domain.TimeRepository;
import roomescape.time.dto.SaveTimeRequest;
import roomescape.time.dto.TimeResponse;

import java.net.URI;
import java.util.List;

@RequestMapping("/times")
@Controller
public class TimeController {
    private final TimeRepository timeRepository;

    public TimeController(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @GetMapping
    public ResponseEntity<List<TimeResponse>> readTimes() {
        List<Time> times = timeRepository.findAll();
        List<TimeResponse> timeResponses = times.stream().map(TimeResponse::from).toList();
        return ResponseEntity.ok(timeResponses);
    }

    @PostMapping
    public ResponseEntity<TimeResponse> createTime(@RequestBody SaveTimeRequest time) {
        Time savedTime = timeRepository.save(time.toEntity());
        TimeResponse timeResponse = TimeResponse.from(savedTime);

        return ResponseEntity.created(URI.create("/times/" + timeResponse.id())).body(timeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        timeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
