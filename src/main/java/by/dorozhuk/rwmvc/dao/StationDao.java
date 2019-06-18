package by.dorozhuk.rwmvc.dao;

import by.dorozhuk.rwmvc.entity.Station;

import java.util.List;

public interface StationDao {

    Station findByName(String name);

    void saveStation(Station station);

    void deleteStation(Station station);

    void updateStation(Station station);

    List<Station> getAll();
}
