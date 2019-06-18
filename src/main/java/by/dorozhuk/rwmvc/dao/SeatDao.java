package by.dorozhuk.rwmvc.dao;

import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Seat;
import by.dorozhuk.rwmvc.entity.Train;

import java.util.List;

public interface SeatDao {

    void add(Seat seat);

    void add(List<Seat> seats);

    void delete(Seat seat);

    void update(Seat seat);

    void delete(List<Seat> seats);

    List<Seat> getAll();

    Seat getById(Long id);

    Seat getByTrainAndCarriageAndSeat(Train train, Carriage carriage, Integer number);
}
