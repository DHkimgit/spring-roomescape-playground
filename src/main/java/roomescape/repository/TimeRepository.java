package roomescape.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import roomescape.domain.Time;

@Repository
public class TimeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public TimeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("time")
                .usingColumns("time")
                .usingGeneratedKeyColumns("id");
    }

    public Time insert(Map<String, String> times) {
        Long newId = simpleJdbcInsert.executeAndReturnKeyHolder(times).getKey().longValue();
        return new Time(newId, times.get("time"));
    }

    public List<Time> finaAll() {
        return jdbcTemplate.query("SELECT * FROM time", (rs, rowNum) -> new Time(rs.getLong("id"), rs.getString("time")));
    }

    public Time findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM time WHERE id = ?", new Object[]{id}, ((rs, rowNum) -> new Time(rs.getLong("id"), rs.getString("time"))));
    }

    public boolean deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM time WHERE id = ?", id) == 1;
    }
}