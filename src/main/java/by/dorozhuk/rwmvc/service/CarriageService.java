package by.dorozhuk.rwmvc.service;

import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Train;
import by.dorozhuk.rwmvc.exception.BusinessLogicException;

import java.util.List;

public interface CarriageService {

    void add(Carriage carriage) throws BusinessLogicException;

    void delete(Long ide);

    void update(Carriage carriage);

    List<Carriage> getAll();

    Carriage getById(Long id);
}
