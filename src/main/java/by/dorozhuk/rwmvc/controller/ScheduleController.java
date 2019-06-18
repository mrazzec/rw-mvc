package by.dorozhuk.rwmvc.controller;

import by.dorozhuk.rwmvc.entity.Schedule;
import by.dorozhuk.rwmvc.exception.DateException;
import by.dorozhuk.rwmvc.exception.EqualsException;
import by.dorozhuk.rwmvc.exception.IntersectionException;
import by.dorozhuk.rwmvc.service.impl.ScheduleServiceImpl;
import by.dorozhuk.rwmvc.service.impl.StationServiceImpl;
import by.dorozhuk.rwmvc.service.impl.TrainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/schedule")
public class ScheduleController {

    @Autowired
    ScheduleServiceImpl scheduleService;

    @Autowired
    StationServiceImpl stationService;

    @Autowired
    TrainServiceImpl trainService;

    @GetMapping
    public ModelAndView schedules() {
        return new ModelAndView("schedule", "schedules", scheduleService.getAll());
    }

    @GetMapping(path = "/add")
    public ModelAndView addSchedule(ModelAndView modelAndView) {
        modelAndView.addObject("scheduleAddForm", new Schedule());
        modelAndView.addObject("stations", stationService.getAll());
        modelAndView.addObject("trains", trainService.getAll());
        modelAndView.setViewName("addschedule");
        return modelAndView;
    }

    @PostMapping(path = "/add")
    public ModelAndView addScheduleForm(@Valid @ModelAttribute("scheduleAddForm") Schedule schedule,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {

        modelAndView.setViewName("redirect:/schedule");
        try {
            scheduleService.add(schedule);

        } catch (IntersectionException intersectionExeption) {
            bindingResult.rejectValue("departureDate", "error.schedule", intersectionExeption.getMessage());
            bindingResult.rejectValue("arrivalDate", "error.schedule", intersectionExeption.getMessage());
            modelAndView.addObject("stations", stationService.getAll());
            modelAndView.addObject("trains", trainService.getAll());
            modelAndView.setViewName("addschedule");
        } catch (EqualsException e) {
            bindingResult.rejectValue("departureStation.name", "error.schedule", e.getMessage());
            bindingResult.rejectValue("arrivalStation.name", "error.schedule", e.getMessage());
            modelAndView.addObject("stations", stationService.getAll());
            modelAndView.addObject("trains", trainService.getAll());
            modelAndView.setViewName("addschedule");
        } catch (DateException e) {
            bindingResult.rejectValue("departureDate", "error.schedule", e.getMessage());
            bindingResult.rejectValue("arrivalDate", "error.schedule", e.getMessage());
            modelAndView.addObject("stations", stationService.getAll());
            modelAndView.addObject("trains", trainService.getAll());
            modelAndView.setViewName("addschedule");
        }
        return modelAndView;
    }

    @GetMapping(path = "/update/{scheduleId}")
    public ModelAndView updateTrain(@PathVariable("scheduleId") String scheduleId, ModelAndView modelAndView) {
        Schedule schedule = scheduleService.getById(Long.parseLong(scheduleId));
        modelAndView.addObject("scheduleAddForm", new Schedule());
        modelAndView.addObject("stations", stationService.getAll());
        modelAndView.addObject("trains", trainService.getAll());
        modelAndView.setViewName("addschedule");
        return modelAndView;
    }

    @PostMapping(path = "/update")
    public ModelAndView updateTrainForm(@Valid @ModelAttribute("scheduleUpdateForm") Schedule schedule,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("updateschedule");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/schedule");

        try {
            scheduleService.update(schedule);
        } catch (IntersectionException intersectionExeption) {
            bindingResult.rejectValue("departureDate", "error.schedule", intersectionExeption.getMessage());
            bindingResult.rejectValue("arrivalDate", "error.schedule", intersectionExeption.getMessage());
            modelAndView.addObject("stations", stationService.getAll());
            modelAndView.addObject("trains", trainService.getAll());
            modelAndView.setViewName("updateschedule");
        } catch (EqualsException e) {
            bindingResult.rejectValue("departureStation.name", "error.schedule", e.getMessage());
            bindingResult.rejectValue("arrivalStation.name", "error.schedule", e.getMessage());
            modelAndView.addObject("stations", stationService.getAll());
            modelAndView.addObject("trains", trainService.getAll());
            modelAndView.setViewName("updateschedule");
        } catch (DateException e) {
            bindingResult.rejectValue("departureDate", "error.schedule", e.getMessage());
            bindingResult.rejectValue("arrivalDate", "error.schedule", e.getMessage());
            modelAndView.addObject("stations", stationService.getAll());
            modelAndView.addObject("trains", trainService.getAll());
            modelAndView.setViewName("updateschedule");
        }
        return modelAndView;
    }

    @GetMapping(path = "/delete/{scheduleId}")
    public ModelAndView deleteStation(@PathVariable("scheduleId") String scheduleId,
                                      ModelAndView modelAndView) {
        scheduleService.delete(Long.parseLong(scheduleId));
        modelAndView.setViewName("redirect:/schedule");
        return modelAndView;
    }


}
