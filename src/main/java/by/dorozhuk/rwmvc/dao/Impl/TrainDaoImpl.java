package by.dorozhuk.rwmvc.dao.Impl;

import by.dorozhuk.rwmvc.dao.TrainDao;
import by.dorozhuk.rwmvc.entity.Train;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class TrainDaoImpl implements TrainDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Train train) {
        sessionFactory.getCurrentSession().save(train);
    }

    @Override
    public void delete(Train train) {
        sessionFactory.getCurrentSession().delete(train);
    }

    @Override
    public void update(Train train) {
        try {
            sessionFactory.getCurrentSession().update(train);
        } catch (NonUniqueObjectException e) {
            sessionFactory.getCurrentSession().merge(train);
        }
    }

    @Override
    public List<Train> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Train ").getResultList();
    }

    @Override
    public Train getById(Long id) {
        return (Train) sessionFactory.getCurrentSession()
                .createQuery("from Train where id =: id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Train getByName(String name) {

        try {
            return (Train) sessionFactory.getCurrentSession()
                    .createQuery("from Train where name = :name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
