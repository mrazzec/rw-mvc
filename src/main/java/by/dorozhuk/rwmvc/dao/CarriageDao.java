package by.dorozhuk.rwmvc.dao;

import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Train;

import java.util.List;

public interface CarriageDao {

    void add(Carriage carriage);

    void delete(Carriage carriage);

    void update(Carriage carriage);

    List<Carriage> getAll();

    Carriage getById(Long id);

    Carriage getByTrainAndNumber(Train train, Integer number);
}
