package by.dorozhuk.rwmvc.controller;

import by.dorozhuk.rwmvc.entity.Station;
import by.dorozhuk.rwmvc.exception.BusinessLogicException;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;
import by.dorozhuk.rwmvc.service.impl.ScheduleServiceImpl;
import by.dorozhuk.rwmvc.service.impl.StationServiceImpl;
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
@RequestMapping(path = "/station")
public class StationController {

    @Autowired
    StationServiceImpl stationService;

    @Autowired
    ScheduleServiceImpl scheduleService;

    @GetMapping
    public ModelAndView stations() {
        return new ModelAndView("station", "stations", stationService.getAll());
    }

    @GetMapping(path = "/add")
    public ModelAndView addStation() {
        return new ModelAndView("addstation", "stationAddForm", new Station());
    }

    @PostMapping(path = "/add")
    public ModelAndView addStationForm(@Valid @ModelAttribute("stationAddForm") Station station,
                                       BindingResult bindingResult,
                                       ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {

            modelAndView.setViewName("addstation");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/station");
        try {
            stationService.saveStation(station);
        } catch (DoesntExistServiceException e) {
            bindingResult.rejectValue("name", "error.station", e.getMessage());
            modelAndView.setViewName("addstation");
        }
        return modelAndView;
    }

    @GetMapping(path = "/{stationName}")
    public  ModelAndView getStation (@PathVariable("stationName") String stationName,
                                     ModelAndView modelAndView) {
        modelAndView.setViewName("stationbyname");
        try {
            modelAndView.addObject("stationName", stationName);
            modelAndView.addObject("schedules", scheduleService.getByStation(stationName));
        } catch (DoesntExistServiceException | BusinessLogicException e) {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @GetMapping(path = "/delete/{stationName}")
    public ModelAndView deleteStation(@PathVariable("stationName") String stationName,
                                      ModelAndView modelAndView) {
        try {
            stationService.deleteStation(stationName);
            modelAndView.setViewName("redirect:/station");
        } catch (DoesntExistServiceException e) {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @GetMapping(path = "/update/{stationName}")
    public ModelAndView updateStation(@PathVariable("stationName") String stationName) {
        Station station = stationService.getByName(stationName);
        station.setNewName(station.getName());
        return new ModelAndView("updatestation", "stationUpdateForm", station);
    }

    @PostMapping(path = "/update")
    public ModelAndView updateStationForm(@Valid @ModelAttribute("stationUpdateForm") Station station,
            BindingResult bindingResult,
            ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("updatestation");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/station");
        try {
            stationService.updateStation(station);
        } catch (DoesntExistServiceException e) {
            bindingResult.rejectValue("name", "error.station", e.getMessage());
            modelAndView.setViewName("updatestation");
        }
        return modelAndView;
    }
}
