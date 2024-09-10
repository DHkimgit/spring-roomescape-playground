package roomescape.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import roomescape.domain.TimeSlot;
import roomescape.dto.TimeSlotCreateCommand;
import roomescape.dto.TimeSlotCreateRequest;
import roomescape.dto.TimeSlotResponse;
import roomescape.service.TimeSlotService;

@RestController
@RequestMapping("/times")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping
    public ResponseEntity<List<TimeSlotResponse>> getTimeSlots() {
        List<TimeSlot> timeSlots = timeSlotService.getTimeSlots();
        var response = timeSlots.stream().map(TimeSlotResponse::from).toList();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<TimeSlotResponse> createTimeSlot(
        @RequestBody TimeSlotCreateRequest req
    ) {
        TimeSlot newTimeSlot = timeSlotService.createTimeSlot(
            new TimeSlotCreateCommand(req.time())
        );
        return ResponseEntity.created(URI.create("/times/" + newTimeSlot.getId()))
            .body(TimeSlotResponse.from(newTimeSlot));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTimeSlot(@PathVariable Long id) {
        timeSlotService.deleteTimeSlot(id);
        return ResponseEntity.noContent().build();
    }
}
