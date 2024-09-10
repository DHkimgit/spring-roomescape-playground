package roomescape.repository;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import roomescape.domain.TimeSlot;

@Repository
@Primary
public class JdbcTimeSlotRepository implements TimeSlotRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTimeSlotRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TimeSlot> timeSlotRowMapper = (resultSet, rowNum) -> {
        return new TimeSlot.Builder()
            .id(resultSet.getLong("id"))
            .time(LocalTime.parse(resultSet.getString("time")))
            .build();
    };

    @Override
    public TimeSlot save(TimeSlot timeSlot) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("time").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("time", timeSlot.getTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new TimeSlot.Builder()
            .id(key.longValue())
            .time(timeSlot.getTime())
            .build();
    }

    @Override
    public List<TimeSlot> findAll() {
        String sql = "SELECT * FROM time";
        return jdbcTemplate.query(sql, timeSlotRowMapper);
    }

    public Optional<TimeSlot> findById(Long id) {
        String sql = "SELECT * FROM time WHERE id = ?";
        List<TimeSlot> timeSlots = jdbcTemplate.query(sql, timeSlotRowMapper, id);
        return timeSlots.stream().findAny();
    }

    @Override
    public void delete(TimeSlot timeSlot) {
        String sql = "DELETE FROM time WHERE id = ?";
        jdbcTemplate.update(sql, timeSlot.getId());
    }
}
