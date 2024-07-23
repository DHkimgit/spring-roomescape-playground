package roomescape.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import roomescape.domain.Time;
import roomescape.dto.RequestTimeDto;
import roomescape.dto.ResponseTimeDto;
import roomescape.service.TimeService;

@Controller
public class TimeController {

    private final TimeService timeService;

    public TimeController(final TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/time")
    public String time() {
        return "time";
    }

    @PostMapping("/times")
    @ResponseBody
    public ResponseEntity<Time> createTime(@Valid @RequestBody RequestTimeDto timeDto) {
        Time time = timeService.createTime(timeDto);

        URI location = getUri(time.getId());

        return ResponseEntity.created(location)
                .body(time);
    }

    private URI getUri(Long id) {
        URI location = UriComponentsBuilder.fromPath("/times/{id}").buildAndExpand(id).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return location;
    }

    @GetMapping("/times")
    @ResponseBody
    public ResponseEntity<List<ResponseTimeDto>> findAllTime() {
        List<ResponseTimeDto> ResponseTimeDtos = timeService.findAll();
        return ResponseEntity.ok(ResponseTimeDtos);
    }

    @DeleteMapping("/times/{id}")
    @ResponseBody
    public ResponseEntity<Time> deleteTimeById(@PathVariable("id") Long id) {
        timeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}