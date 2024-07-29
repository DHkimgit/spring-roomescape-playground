package roomescape.domain.time.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.dto.ReservationResponse;
import roomescape.domain.time.ReservationTime;
import roomescape.domain.time.dto.TimeRequest;
import roomescape.domain.time.dto.TimeResponse;

import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TimeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/time")
    public String timePage() {
        return "time";
    }

    @PostMapping("/times")
    public ResponseEntity<TimeResponse> addTime(@Valid @RequestBody TimeRequest timeRequest) {
        ReservationTime reservationTime = new ReservationTime(timeRequest.getTime());

        String sql = "INSERT INTO time (time) VALUES (?)";
        jdbcTemplate.update(sql, reservationTime.getTime());

        Long newId = jdbcTemplate.queryForObject("SELECT count(*) from time", Long.class);
        reservationTime.setId(newId);

        TimeResponse timeResponse = new TimeResponse(reservationTime.getId(), reservationTime.getTime());

        URI location = URI.create(String.format("/times/%d", newId));
        return ResponseEntity.created(location).body(timeResponse);
    }

    @GetMapping("/times")
    public ResponseEntity<List<TimeResponse>> getTimes() {
        String sql = "SELECT id, time FROM time";
        List<ReservationTime> times = jdbcTemplate.query(sql, (resultSet, rowNum) -> (
            new ReservationTime(
                resultSet.getLong("id"),
                resultSet.getTime("time").toLocalTime()
            )
        ));

        List<TimeResponse> responses = times.stream()
                .map(TimeResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        String sql = "DELETE FROM time WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
