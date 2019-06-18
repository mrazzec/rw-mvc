package by.dorozhuk.rwmvc.service.impl;

import by.dorozhuk.rwmvc.dao.Impl.CarriageDaoImpl;
import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Seat;
import by.dorozhuk.rwmvc.exception.BusinessLogicException;
import by.dorozhuk.rwmvc.service.CarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CarriageServiceImpl implements CarriageService {

    @Autowired
    CarriageDaoImpl carriageDao;

    @Autowired
    SeatServiceImpl seatService;

    @Override
    public void add(Carriage carriage ) throws BusinessLogicException {
        if (carriage.getCountSeats() <= 0)
            throw new BusinessLogicException("wrong count");

        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= carriage.getCountSeats(); i++) {
            seats.add(new Seat(i));
        }
        carriage.setSeats(seats);
        carriageDao.add(carriage);
    }

    @Override
    public void delete(Long id) {
        carriageDao.delete(carriageDao.getById(id));
    }

    @Override
    public void update(Carriage carriage) {
        Carriage byId = carriageDao.getById(carriage.getId());
        byId.setNewCountSeats(carriage.getNewCountSeats());
        List<Seat> seats = byId.getSeats();
        Collections.sort(seats);
        if (byId.getNewCountSeats() < byId.getCountSeats()) {
            List<Seat> listToRemove = new ArrayList<>();
            for (int i = byId.getCountSeats() - 1 ; i > byId.getNewCountSeats() - 1 ; i--) {
                listToRemove.add(seats.get(i));
                seats.remove(i);
            }
            seatService.delete(listToRemove);
        } else if (byId.getNewCountSeats() > byId.getCountSeats()) {
            List<Seat> listToAdd = new ArrayList<>();
            for (int i = byId.getCountSeats() + 1; i <= byId.getNewCountSeats(); i++) {
                Seat seatToAdd = new Seat(i);
                listToAdd.add(seatToAdd);
                seats.add(seatToAdd);
            }
            seatService.add(listToAdd);
        }
        byId.setSeats(seats);
        byId.setCountSeats(carriage.getNewCountSeats());
        byId.setNewCountSeats(null);
        carriageDao.update(byId);
    }

    @Override
    public List<Carriage> getAll() {
        return carriageDao.getAll();
    }

    @Override
    public Carriage getById(Long id) {
        return carriageDao.getById(id);
    }
}
