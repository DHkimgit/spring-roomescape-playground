package roomescape.domain.reservation.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.dto.ReservationRequest;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_RESERVATION_SQL = "INSERT INTO reservation(name, date, time_id) VALUES (?, ?, ?)";
    private static final String SELECT_LAST_INSERT_ID_SQL = "SELECT LAST_INSERT_ID()";
    private static final String SELECT_TIME_SQL = "SELECT time FROM time WHERE id = ?";
    private static final String SELECT_RESERVATIONS_SQL =
            "SELECT r.id as reservation_id, r.name, r.date, t.id as time_id, t.time as time_value " +
                    "FROM reservation as r INNER JOIN time as t ON r.time_id = t.id";
    private static final String DELETE_RESERVATION_SQL = "DELETE FROM reservation WHERE id = ?";

    public ReservationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Reservation addReservation(ReservationRequest request) {
        jdbcTemplate.update(INSERT_RESERVATION_SQL, request.getName(), request.getDate(), request.getTimeId());

        Long newId = jdbcTemplate.queryForObject(SELECT_LAST_INSERT_ID_SQL, Long.class);
        Time time = jdbcTemplate.queryForObject(SELECT_TIME_SQL, Time.class, request.getTimeId());

        return new Reservation(newId, request.getName(), request.getDate(), request.getTimeId(), time);
    }

    public List<Reservation> getReservations() {
        return jdbcTemplate.query(SELECT_RESERVATIONS_SQL, (resultSet, rowNum) -> {
            Long reservationId = resultSet.getLong("reservation_id");
            String name = resultSet.getString("name");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            Long timeId = resultSet.getLong("time_id");
            Time timeValue = resultSet.getTime("time_value");

            return new Reservation(reservationId, name, date, timeId, timeValue);
        });
    }

    public boolean deleteReservation(Long id) {
        int rowsAffected = jdbcTemplate.update(DELETE_RESERVATION_SQL, id);
        return rowsAffected > 0;
    }
}
