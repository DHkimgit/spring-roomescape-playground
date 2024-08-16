package roomescape.dao;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.dto.ReservationCreateRequestDto;
import roomescape.entity.Reservation;

@Repository
public class QueryDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Reservation> actorRowMapper = (resultSet, rowNum) -> new Reservation(
        resultSet.getLong("id"),
        resultSet.getString("name"),
        resultSet.getString("date"),
        resultSet.getString("time")
    );

    public QueryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reservation> getAllReservations() {
        return jdbcTemplate.query("SELECT id, name, date, time FROM reservation", actorRowMapper);
    }

    public Reservation insertReservation(ReservationCreateRequestDto request) {
        String sql = "INSERT INTO reservation(name, date, time) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            // id가 auto_increment됨
            PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, request.getName());
            ps.setString(2, request.getDate());
            ps.setString(3, request.getTime());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        return new Reservation(id, request.getName(), request.getDate(), request.getTime());
    }

    public Integer deleteReservation(Long id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        return jdbcTemplate.update(sql,id);
    }
}
