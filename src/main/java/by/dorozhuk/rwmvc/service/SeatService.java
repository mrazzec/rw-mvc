package by.dorozhuk.rwmvc.service;

import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Seat;
import by.dorozhuk.rwmvc.entity.Train;

import java.util.List;

public interface SeatService {

    void add(Seat seat);

    void add(List<Seat> seats);

    void delete(Seat seat);

    void delete(List<Seat> seats);

    void update(Seat seat);

    List<Seat> getAll();

    Seat getById(Long id);

    Seat getByTrainAndCarriageAndSeat(Train train, Carriage carriage, Integer number);
}
