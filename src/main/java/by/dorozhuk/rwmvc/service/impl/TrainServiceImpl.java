package by.dorozhuk.rwmvc.service.impl;

import by.dorozhuk.rwmvc.dao.Impl.TrainDaoImpl;
import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Train;
import by.dorozhuk.rwmvc.exception.BusinessLogicException;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;
import by.dorozhuk.rwmvc.service.TrainService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    private Logger logger = Logger.getLogger(TrainServiceImpl.class.getName());

    @Autowired
    TrainDaoImpl trainDao;

    @Autowired
    CarriageServiceImpl carriageService;

    @Override
    public void add(Train train) throws DoesntExistServiceException {
        if (getByName(train.getName()) != null)
            throw new DoesntExistServiceException("Train already exist");

        train.setCarriages(new ArrayList<>());
        trainDao.add(train);
        logger.info("train: " + train + " added");
    }

    @Override
    public void delete(String name) throws DoesntExistServiceException {
        Train train = getByName(name);
        if (train == null)
            throw new DoesntExistServiceException("wrong name");

        trainDao.delete(train);
        logger.info(train + " deleted");
    }

    @Override
    public void update(Carriage carriage) throws BusinessLogicException {
        Train train = getByName(carriage.getTrain().getName());
        carriage.setTrain(train);

        carriageService.add(carriage);
        List<Carriage> carriages = train.getCarriages();
        carriages.add(carriage);
        train.setCarriages(carriages);
        trainDao.update(train);
    }

    @Override
    public void update(Train train) throws DoesntExistServiceException {
        if (getByName(train.getNewName()) != null && !train.getName().equals(train.getNewName()))
            throw new DoesntExistServiceException("Train already exist");

        train.setName(train.getNewName());
        train.setNewName(null);
        trainDao.update(train);
        logger.info("train " + train + " updated");
    }

    @Override
    public List<Train> getAll() {
        return trainDao.getAll();
    }

    @Override
    public Train getById(Long id) {
        return (Train) trainDao.getById(id);
    }

    @Override
    public Train getByName(String name) {
        return trainDao.getByName(name);
    }
}
