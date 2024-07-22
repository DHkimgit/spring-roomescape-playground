package roomescape.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.dto.RequestTime;
import roomescape.dto.ResponseTime;
import roomescape.service.TimeService;

import java.util.List;

@Controller
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/time")
    public String time() {
        return "time";
    }

    @PostMapping("/times")
    @ResponseBody
    public ResponseEntity<ResponseTime> addTime(@RequestBody RequestTime requestTime) {
        ResponseTime newTime = timeService.addTime(requestTime.time());
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/times/" + newTime.id())
                .body(newTime);
    }

    @GetMapping("/times")
    @ResponseBody
    public ResponseEntity<List<ResponseTime>> getTimes() {
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(timeService.getTimes());
    }

    @DeleteMapping("times/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        timeService.deleteTime(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }
}
