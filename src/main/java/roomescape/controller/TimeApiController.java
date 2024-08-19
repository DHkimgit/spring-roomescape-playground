package roomescape.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.TimeReqDto;
import roomescape.dto.TimeResDto;
import roomescape.service.TimeService;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeApiController {
    private final TimeService timeService;

    public TimeApiController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<TimeResDto> createTime(@Valid @RequestBody TimeReqDto reqDto) {
        TimeResDto resDto = timeService.createTime(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/times/" + resDto.id())
                .body(resDto);
    }

    @GetMapping
    public ResponseEntity<List<TimeResDto>> getAllTimes() {
        List<TimeResDto> times = timeService.findAllTimes();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(times);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        timeService.deleteTime(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }
}
