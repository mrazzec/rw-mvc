package by.dorozhuk.rwmvc.dao.Impl;

import by.dorozhuk.rwmvc.dao.SeatDao;
import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Seat;
import by.dorozhuk.rwmvc.entity.Train;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatDaoImpl implements SeatDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Seat seat) {
        sessionFactory.getCurrentSession().save(seat);
    }

    @Override
    public void add(List<Seat> seats) {
        for (int i = 0; i < seats.size(); i++) {
            sessionFactory.getCurrentSession().save(seats.get(i));
        }
    }

    @Override
    public void delete(Seat seat) {
        sessionFactory.getCurrentSession().delete(seat);
    }

    @Override
    public void update(Seat seat) {
        sessionFactory.getCurrentSession().delete(seat);
    }

    @Override
    public void delete(List<Seat> seats) {
        for (int i = 0; i < seats.size(); i++) {
            sessionFactory.getCurrentSession().delete(seats.get(i));
        }

    }

    @Override
    public List<Seat> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Seat ").getResultList();
    }

    @Override
    public Seat getById(Long id) {
        return sessionFactory.getCurrentSession().get(Seat.class, id);
    }

    @Override
    public Seat getByTrainAndCarriageAndSeat(Train train, Carriage carriage, Integer number) {
        return (Seat) sessionFactory.getCurrentSession()
                .createQuery("from Seat where Train  =: train and Carriage =: carriage and  number =: number" )
                .setParameter("train", train)
                .setParameter("carriage", carriage)
                .setParameter("number", number)
                .getSingleResult();
    }
}
