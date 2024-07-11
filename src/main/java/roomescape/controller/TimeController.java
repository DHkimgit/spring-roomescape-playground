package roomescape.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Time;
import roomescape.dto.TimeDto;
import roomescape.service.TimeService;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Time> addTime(@RequestBody TimeDto timeDto) {
        Time time = timeService.addTime(timeDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/times/" + time.getId());
        return new ResponseEntity<>(time, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Time>> getAllTimes() {
        List<Time> times = timeService.getAllTimes();
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable int id) {
        timeService.deleteTime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
