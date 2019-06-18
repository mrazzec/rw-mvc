package by.dorozhuk.rwmvc.dao.Impl;

import by.dorozhuk.rwmvc.dao.ScheduleDao;
import by.dorozhuk.rwmvc.entity.Schedule;
import by.dorozhuk.rwmvc.entity.Station;
import by.dorozhuk.rwmvc.entity.Train;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Schedule schedule) {
        sessionFactory.getCurrentSession().save(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        sessionFactory.getCurrentSession().delete(schedule);
    }

    @Override
    public void update(Schedule schedule) {
        sessionFactory.getCurrentSession().update(schedule);
    }

    @Override
    public List<Schedule> getByStation(Station station) {
        return sessionFactory.getCurrentSession()
                .createQuery(" from Schedule where arrivalStation = :Station or " +
                        "departureStation = :Station order by train.name desc ")
                .setParameter("Station", station)
                .getResultList();
    }

    @Override
    public List<Schedule> getByTrain(Train train) {
        return sessionFactory.getCurrentSession()
                .createQuery( "from Schedule where train = :Train " +
                        "order by departureDate asc ")
                .setParameter("Train", train)
                .getResultList();
    }

    @Override
    public List<Schedule> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Schedule ").getResultList();
    }

    @Override
    public Schedule getById(Long id) {
        return (Schedule) sessionFactory.getCurrentSession()
                .createQuery("from Schedule where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Schedule> getCheckIntersection(Schedule schedule) {
        return sessionFactory.getCurrentSession().createQuery(" from Schedule where train = :train " +
                "and ((:dDate between departureDate and arrivalDate) or " +
                "(:aDate between departureDate and arrivalDate))" +
                "order by departureDate desc" )
                .setParameter("train", schedule.getTrain())
                .setParameter("dDate", schedule.getDepartureDate())
                .setParameter("aDate", schedule.getArrivalDate())
                .getResultList();
    }
}
