package by.dorozhuk.rwmvc.service.impl;

import by.dorozhuk.rwmvc.dao.Impl.StationDaoImpl;
import by.dorozhuk.rwmvc.entity.Station;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;
import by.dorozhuk.rwmvc.service.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    private Logger logger = Logger.getLogger(StationServiceImpl.class.getName());

    @Autowired
    StationDaoImpl stationDAO;

    @Override
    public void saveStation(Station station) throws DoesntExistServiceException {
        if (getByName(station.getName()) != null)
            throw new DoesntExistServiceException("Station already exist");
        stationDAO.saveStation(station);
        logger.info("station " + station + " added");
    }

    @Override
    public void deleteStation(String name) throws DoesntExistServiceException {
        Station station = getByName(name);
        if (station == null)
            throw new DoesntExistServiceException("wrong name");

        stationDAO.deleteStation(station);
        logger.info(station + " deleted");
    }

    @Override
    public void updateStation(Station station) throws DoesntExistServiceException {
        if (getByName(station.getNewName()) != null && !station.getName().equals(station.getNewName()))
            throw new DoesntExistServiceException("Station already exist");

        station.setName(station.getNewName());
        station.setNewName(null);
        stationDAO.updateStation(station);
        logger.info("station " + station + " updated");
    }

    @Override
    public List<Station> getAll() {
        return stationDAO.getAll();
    }

    @Override
    public Station getByName(String name) {
        return stationDAO.findByName(name);
    }
}
