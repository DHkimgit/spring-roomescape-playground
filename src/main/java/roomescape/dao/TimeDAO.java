package roomescape.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.domain.Time;
import roomescape.dto.TimeDto;

import java.util.List;

@Repository
public class TimeDAO {
    private final JdbcTemplate jdbcTemplate;

    public TimeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Time time) {
        jdbcTemplate.update("INSERT INTO time (time) VALUES (?)",
                time.getTime());
    }

    public int getId(Time time) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT id FROM time WHERE time = ? LIMIT 1",
                    Integer.class, time.getTime()
            );
        } catch (Exception e) {
            throw new RuntimeException("찾는 id가 존재하지 않습니다!");
        }
    }

    public List<Time> findAll() {
        return jdbcTemplate.query("SELECT * FROM time", (rs, rowNum) ->
                new Time(rs.getInt("id"), rs.getString("time"))
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM time WHERE id = ?", id);
    }

    public Time findByTime(String time) {
        try {
            return jdbcTemplate.queryForObject("SELECT id, time FROM time WHERE time = ? LIMIT 1",
                    (resultSet, rowNum) -> new Time(resultSet.getInt("id"), resultSet.getString("time")),
                    time);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
