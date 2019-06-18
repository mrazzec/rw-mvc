package by.dorozhuk.rwmvc.service.impl;

import by.dorozhuk.rwmvc.dao.Impl.SeatDaoImpl;
import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Seat;
import by.dorozhuk.rwmvc.entity.Train;
import by.dorozhuk.rwmvc.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatDaoImpl seatDao;

    @Override
    public void add(Seat seat) {
        seatDao.add(seat);
    }

    @Override
    public void add(List<Seat> seats) {
        seatDao.add(seats);
    }

    @Override
    public void delete(Seat seat) {
        seatDao.delete(seat);
    }

    @Override
    public void delete(List<Seat> seats) {
        seatDao.delete(seats);
    }

    @Override
    public void update(Seat seat) {
        seatDao.update(seat);
    }

    @Override
    public List<Seat> getAll() {
        return seatDao.getAll();
    }

    @Override
    public Seat getById(Long id) {
        return seatDao.getById(id);
    }

    @Override
    public Seat getByTrainAndCarriageAndSeat(Train train, Carriage carriage, Integer number) {
        return seatDao.getByTrainAndCarriageAndSeat(train, carriage, number);
    }
}
