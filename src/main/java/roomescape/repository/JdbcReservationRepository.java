package roomescape.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.exception.ReservationNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Repository
@Primary
public class JdbcReservationRepository implements ReservationRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Reservation> reservationRowMapper = (resultSet, rowNum) -> {
        return new Reservation.Builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .date(LocalDate.parse(resultSet.getString("date")))
            .time(LocalTime.parse(resultSet.getString("time")))
            .build();
    };

    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservation";
        return jdbcTemplate.query(sql, reservationRowMapper);
    }

    public Optional<Reservation> findById(Long id) {
        String sql = "SELECT * FROM reservation WHERE id = ?";
        List<Reservation> result = jdbcTemplate.query(sql, reservationRowMapper, id);
        return result.stream().findAny();
    }

    public Reservation save(Reservation reservation) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("reservation").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", reservation.getName());
        parameters.put("date", reservation.getDate());
        parameters.put("time", reservation.getTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new Reservation.Builder()
            .id(key.longValue())
            .name(reservation.getName())
            .date(reservation.getDate())
            .time(reservation.getTime())
            .build();
    }

    public Reservation saveWithKeyHolder(Reservation reservation) {
        String sql = "INSERT INTO reservation (name, date, time) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, reservation.getName());
            ps.setString(2, reservation.getDate().toString());
            ps.setString(3, reservation.getTime().toString());
            return ps;
        }, keyHolder);
        return findById(Objects.requireNonNull(keyHolder.getKey()).longValue())
            .orElseThrow(ReservationNotFoundException::new);
    }

    public void delete(Reservation reservation) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        jdbcTemplate.update(sql, reservation.getId());
    }
}
