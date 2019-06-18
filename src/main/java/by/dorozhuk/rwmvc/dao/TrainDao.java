package by.dorozhuk.rwmvc.dao;

import by.dorozhuk.rwmvc.entity.Train;

import java.util.List;

public interface TrainDao {

    void add(Train train);

    void delete(Train train);

    void update(Train train);

    List<Train> getAll();

    Train getById(Long id);

    Train getByName(String name);
}
