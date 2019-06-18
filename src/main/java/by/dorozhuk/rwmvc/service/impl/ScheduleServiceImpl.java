package by.dorozhuk.rwmvc.service.impl;

import by.dorozhuk.rwmvc.dao.Impl.ScheduleDaoImpl;
import by.dorozhuk.rwmvc.entity.Schedule;
import by.dorozhuk.rwmvc.entity.Station;
import by.dorozhuk.rwmvc.entity.Train;
import by.dorozhuk.rwmvc.exception.BusinessLogicException;
import by.dorozhuk.rwmvc.exception.DateException;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;
import by.dorozhuk.rwmvc.exception.EqualsException;
import by.dorozhuk.rwmvc.exception.IntersectionException;
import by.dorozhuk.rwmvc.exception.UserDoesntExistException;
import by.dorozhuk.rwmvc.service.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private Logger logger = Logger.getLogger(StationServiceImpl.class.getName());

    @Autowired
    ScheduleDaoImpl scheduleDao;

    @Autowired
    TrainServiceImpl trainService;

    @Autowired
    StationServiceImpl stationService;

    @Override
    public void add(Schedule schedule) throws IntersectionException, EqualsException, DateException {
        Train train = trainService.getByName(schedule.getTrain().getName());
        Station stationA = stationService.getByName(schedule.getArrivalStation().getName());
        Station stationD = stationService.getByName(schedule.getDepartureStation().getName());

//        if (stationA == null || stationD == null || train == null)
        Date departureDate = schedule.getDepartureDate();
        System.out.println(departureDate + "%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        Date arrivalDate = schedule.getArrivalDate();
        System.out.println(arrivalDate + "^^^^^^^^^^^^^^^^^^^^^^^^^");

        Schedule scheduleToDb = new Schedule();
        scheduleToDb.setArrivalStation(stationA);
        scheduleToDb.setDepartureStation(stationD);
        scheduleToDb.setArrivalDate(arrivalDate);
        scheduleToDb.setDepartureDate(departureDate);
        scheduleToDb.setTrain(train);



        if (!getCheckIntersection(scheduleToDb).isEmpty()) {
            throw new IntersectionException("Пересечение расписаний");
        }

        if (stationA.equals(stationD)) {
            throw new EqualsException("Одинаковые станции");
        }

        if (arrivalDate.equals(departureDate)) {
            throw new EqualsException("Одинаковые даты");
        }

        if (arrivalDate.before(departureDate)) {
            throw new DateException("Время отправления до времени прибытия");
        }

        scheduleDao.add(scheduleToDb);
        logger.info(scheduleToDb + " added");
    }

    @Override
    public void delete(Long id) {
        scheduleDao.delete(getById(id));
    }

    @Override
    public List<Schedule> getByStation(String stationName) throws DoesntExistServiceException, BusinessLogicException {
        Station station = stationService.getByName(stationName);
        if (station == null)
            throw new DoesntExistServiceException("Нету такой станции");

        List<Schedule> schedules = scheduleDao.getByStation(station);
        if (schedules.size() == 0) {
            throw new BusinessLogicException("Нет движений на станции");
        }

        List<Schedule> scheduleList = new ArrayList<>();
        for (Schedule schedule :
                schedules) {
            if (schedule.getDepartureStation().getName().equals(stationName)) {
                Schedule newSchedule = new Schedule();
                newSchedule.setDepartureStation(schedule.getDepartureStation());
                newSchedule.setDepartureDate(schedule.getDepartureDate());
                newSchedule.setTrain(schedule.getTrain());
                scheduleList.add(newSchedule);
            } else {
                Schedule newSchedule = new Schedule();
                newSchedule.setArrivalStation(schedule.getArrivalStation());
                newSchedule.setArrivalDate(schedule.getArrivalDate());
                newSchedule.setTrain(schedule.getTrain());
                scheduleList.add(newSchedule);
            }
        }

        return scheduleList;
    }

    @Override
    public List<Schedule> getByTrain(String trainName) throws DoesntExistServiceException, BusinessLogicException {
        Train train = trainService.getByName(trainName);

        if (train == null)
            throw new DoesntExistServiceException("Нету такого поезда");

        List<Schedule> schedules = scheduleDao.getByTrain(train);
        if (schedules.size() == 0) {
            throw new BusinessLogicException("Не проезжает");
        }

        return schedules;
    }

    @Override
    public void update(Schedule schedule) throws IntersectionException, EqualsException, DateException {
        Schedule scheduleOld = getById(schedule.getId());

        Train train = trainService.getByName(schedule.getTrain().getName());
        Station departureStation = stationService.getByName(schedule.getDepartureStation().getName());
        Station arrivalStation = stationService.getByName(schedule.getArrivalStation().getName());
        Date departureDate = schedule.getDepartureDate();
        Date arrivalDate = schedule.getArrivalDate();

        scheduleOld.setTrain(train);
        scheduleOld.setDepartureStation(departureStation);
        scheduleOld.setArrivalStation(arrivalStation);
        scheduleOld.setDepartureDate(departureDate);
        scheduleOld.setArrivalDate(arrivalDate);

        if (getCheckIntersection(scheduleOld).size() > 1) {
            throw new IntersectionException("Пересечение расписаний");
        }

        if (arrivalStation.equals(departureStation)) {
            throw new EqualsException("Одинаковые станции");
        }

        if (arrivalDate.equals(departureDate)) {
            throw new EqualsException("Одинаковые даты");
        }

        if (arrivalDate.before(departureDate)) {
            throw new DateException("Время отправления до времени прибытия");
        }

        scheduleDao.update(scheduleOld);
        logger.info(scheduleOld + " updated");
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleDao.getAll();
    }

    @Override
    public Schedule getById(Long id) {
        return scheduleDao.getById(id);
    }

    @Override
    public List<Schedule> getCheckIntersection(Schedule schedule) {
        return scheduleDao.getCheckIntersection(schedule);
    }

}
