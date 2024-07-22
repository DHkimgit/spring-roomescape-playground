package roomescape.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import roomescape.model.Reservation;
import roomescape.model.Time;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReservationJdbcDAO implements ReservationDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ReservationJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("reservation")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Reservation save(String name, String date, Long timeId) {
        Time time = getTimeById(timeId);
        Map<String, Object> parameters = getParametersMapForInsert(name, date, timeId);
        long newId = (long) simpleJdbcInsert.executeAndReturnKey(parameters);
        return new Reservation(newId, name, date, time);
    }

    @Override
    public List<Reservation> findAll() {
        String query = """
            SELECT
                r.id as reservation_id,
                r.name,
                r.date,
                t.id as time_id,
                t.time as time_value
            FROM reservation as r inner join time as t on r.time_id = t.id""";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Long reservationId = rs.getLong("reservation_id");
            String name = rs.getString("name");
            String date = rs.getString("date");
            Long timeId = rs.getLong("time_id");
            String timeValue = rs.getString("time_value");
            return new Reservation(reservationId, name, date, new Time(timeId, timeValue));
        });

    }

    @Override
    public void deleteById(Long id) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM reservation WHERE id = ?", id);
        if (rowsAffected == 0) {
            throw new IllegalArgumentException("존재하지 않는 예약입니다.");
        }
    }

    private Time getTimeById(Long timeId) {
        String query = "SELECT * FROM time WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String time = rs.getString("time");
            return new Time(id, time);
        }, timeId);
    }

    private Map<String, Object> getParametersMapForInsert(String name, String date, Long time) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("date", date);
        parameters.put("time_id", time);
        return parameters;
    }
}
