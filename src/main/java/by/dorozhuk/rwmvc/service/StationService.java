package by.dorozhuk.rwmvc.service;

import by.dorozhuk.rwmvc.entity.Station;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;

import java.util.List;

public interface StationService {

    void saveStation(Station station) throws DoesntExistServiceException;

    void deleteStation(String name) throws DoesntExistServiceException;

    void updateStation(Station station) throws DoesntExistServiceException;

    List<Station> getAll();

    Station getByName(String name);

}
