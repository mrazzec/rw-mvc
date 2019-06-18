package by.dorozhuk.rwmvc.dao.Impl;

import by.dorozhuk.rwmvc.dao.StationDao;
import by.dorozhuk.rwmvc.entity.Station;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class StationDaoImpl implements StationDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Station findByName(String name) {
        try {
            return (Station) sessionFactory.getCurrentSession()
                    .createQuery("from Station where name =:name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public void saveStation(Station station) {
        sessionFactory.getCurrentSession()
                .save(station);
    }

    @Override
    public void deleteStation(Station station) {
        sessionFactory.getCurrentSession()
                .delete(station);
    }

    @Override
    public void updateStation(Station station) {
       sessionFactory.getCurrentSession()
               .update(station);
    }

    @Override
    public List<Station> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Station")
                .getResultList();
    }
}
