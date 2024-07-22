package roomescape.dao;

import roomescape.model.Time;

import java.util.List;

public interface TimeDAO {
    Time save(String time);
    List<Time> findAll();
    void deleteById(Long id);
}
