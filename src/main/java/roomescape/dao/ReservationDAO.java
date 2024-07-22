package roomescape.dao;

import roomescape.model.Reservation;

import java.util.List;

public interface ReservationDAO {
    Reservation save(String name, String date, Long timeId);
    List<Reservation> findAll();
    void deleteById(Long id);
}
