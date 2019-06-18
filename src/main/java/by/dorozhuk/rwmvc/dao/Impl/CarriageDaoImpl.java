package by.dorozhuk.rwmvc.dao.Impl;

import by.dorozhuk.rwmvc.dao.CarriageDao;
import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Train;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarriageDaoImpl implements CarriageDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Carriage carriage) {
        sessionFactory.getCurrentSession().save(carriage);
    }

    @Override
    public void delete(Carriage carriage) {
        sessionFactory.getCurrentSession().delete(carriage);
    }

    @Override
    public void update(Carriage carriage) {
        sessionFactory.getCurrentSession().update(carriage);
    }

    @Override
    public List<Carriage> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Carriage").getResultList();
    }

    @Override
    public Carriage getById(Long id) {
        return (Carriage) sessionFactory.getCurrentSession()
                .createQuery("from Carriage where id =: id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Carriage getByTrainAndNumber(Train train, Integer number) {
        return (Carriage) sessionFactory.getCurrentSession()
                .createQuery("from Carriage where Train  =: train and number =: number" )
                .setParameter("train", train)
                .setParameter("number", number)
                .getSingleResult();
    }
}
