package roomescape.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import roomescape.model.Time;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TimeJdbcDAO implements TimeDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public TimeJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("time")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Time save(String time) {
        Map<String, Object> parameters = getParametersMapForInsert(time);
        long newId = (long) simpleJdbcInsert.executeAndReturnKey(parameters);
        return new Time(newId, time);
    }

    @Override
    public List<Time> findAll() {
        return jdbcTemplate.query("SELECT * FROM time",
                (rs, rowNum) -> new Time(
                        rs.getLong("id"),
                        rs.getString("time")
                ));
    }

    @Override
    public void deleteById(Long id) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM time WHERE id = ?", id);
        if (rowsAffected == 0) {
            throw new IllegalArgumentException("No time with the given id exists.");
        }
    }

    private Map<String, Object> getParametersMapForInsert(String time) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("time", time);
        return parameters;
    }
}
