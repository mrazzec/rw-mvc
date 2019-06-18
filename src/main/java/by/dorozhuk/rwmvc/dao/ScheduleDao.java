package by.dorozhuk.rwmvc.dao;

import by.dorozhuk.rwmvc.entity.Schedule;
import by.dorozhuk.rwmvc.entity.Station;
import by.dorozhuk.rwmvc.entity.Train;

import java.util.List;

public interface ScheduleDao {

    void add(Schedule schedule);

    void delete(Schedule schedule);

    void update(Schedule schedule);

    List<Schedule> getAll();

    Schedule getById(Long id);

    List<Schedule> getCheckIntersection(Schedule schedule);

    List<Schedule> getByStation(Station station);

    List<Schedule> getByTrain(Train train);
}
