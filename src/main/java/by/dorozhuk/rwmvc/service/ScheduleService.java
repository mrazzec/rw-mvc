package by.dorozhuk.rwmvc.service;

import by.dorozhuk.rwmvc.entity.Schedule;
import by.dorozhuk.rwmvc.exception.BusinessLogicException;
import by.dorozhuk.rwmvc.exception.DateException;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;
import by.dorozhuk.rwmvc.exception.EqualsException;
import by.dorozhuk.rwmvc.exception.IntersectionException;

import java.util.Date;
import java.util.List;

public interface ScheduleService {

    void add(Schedule schedule) throws IntersectionException, EqualsException, DateException;

    void delete(Long id);

    void update(Schedule schedule) throws IntersectionException, EqualsException, DateException;

    List<Schedule> getAll();

    List<Schedule> getByStation(String stationName) throws DoesntExistServiceException, BusinessLogicException;

    List<Schedule> getByTrain(String trainName) throws DoesntExistServiceException, BusinessLogicException;

    Schedule getById(Long id);

    List<Schedule> getCheckIntersection(Schedule schedule);
}
