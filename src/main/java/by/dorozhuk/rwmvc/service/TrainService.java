package by.dorozhuk.rwmvc.service;

import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Train;
import by.dorozhuk.rwmvc.exception.BusinessLogicException;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;

import java.util.List;

public interface TrainService {

    void add(Train train) throws DoesntExistServiceException;

    void delete(String name) throws DoesntExistServiceException;

    void update(Carriage carriage) throws BusinessLogicException;

    void update(Train train) throws DoesntExistServiceException;

    List<Train> getAll();

    Train getById(Long id);

    Train getByName(String name);
}
