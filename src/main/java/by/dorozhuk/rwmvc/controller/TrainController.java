package by.dorozhuk.rwmvc.controller;

import by.dorozhuk.rwmvc.entity.Carriage;
import by.dorozhuk.rwmvc.entity.Train;
import by.dorozhuk.rwmvc.exception.BusinessLogicException;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;
import by.dorozhuk.rwmvc.service.impl.CarriageServiceImpl;
import by.dorozhuk.rwmvc.service.impl.ScheduleServiceImpl;
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
@RequestMapping(path = "/train")
public class TrainController {

    @Autowired
    TrainServiceImpl trainService;

    @Autowired
    CarriageServiceImpl carriageService;

    @Autowired
    ScheduleServiceImpl scheduleService;

    @GetMapping
    public ModelAndView trains(ModelAndView modelAndView) {
        modelAndView.addObject("trains", trainService.getAll());
        modelAndView.addObject("carriages", carriageService.getAll());
        return modelAndView;
    }

    @GetMapping(path = "/add")
    public ModelAndView addCarriage() {
        return new ModelAndView("addtrain", "trainAddForm", new Train());
    }

    @PostMapping(path = "/add")
    public ModelAndView addStationForm(@Valid @ModelAttribute("trainAddForm") Train train,
                                       BindingResult bindingResult,
                                       ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {

            modelAndView.setViewName("addtrain");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/train");
        try {
            trainService.add(train);
        } catch (DoesntExistServiceException e) {
            bindingResult.rejectValue("name", "error.train", e.getMessage());
            modelAndView.setViewName("addtrain");
        }
        return modelAndView;
    }

    @GetMapping(path = "/addcarriage/{trainName}")
    public ModelAndView createNewCarriage(@PathVariable("trainName") String trainName) {
        Train train = trainService.getByName(trainName);
        return new ModelAndView("addcarriage", "carriageAddForm", new Carriage(train));
    }

    @PostMapping(path = "/addcarriage")
    public ModelAndView addCarriageToTrain(@Valid @ModelAttribute("carriageAddForm") Carriage carriage,
                                           BindingResult bindingResult,
                                           ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/train");
        try {

            trainService.update(carriage);
        } catch (BusinessLogicException e) {
            bindingResult.rejectValue("countSeats", "error.carriage", e.getMessage());
            modelAndView.setViewName("addcarriage");
        }
        return modelAndView;
    }

    @GetMapping(path = "/update/{trainName}")
    public ModelAndView updateTrain(@PathVariable("trainName") String trainName) {
        Train train = trainService.getByName(trainName);
        train.setNewName(train.getName());
        return new ModelAndView("updatetrain", "trainUpdateForm", train);
    }

    @PostMapping(path = "/update")
    public ModelAndView updateTrainForm(@Valid @ModelAttribute("trainUpdateForm") Train train,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {
        Train trainFromDb = trainService.getById(train.getId());
        train.setCarriages(trainFromDb.getCarriages());
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("updatetrain");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/train");
        try {


            trainService.update(train);
        } catch (DoesntExistServiceException e) {
            bindingResult.rejectValue("newName", "error.station", e.getMessage());
            modelAndView.setViewName("updatetrain");

        }
        return modelAndView;
    }

    @GetMapping(path = "/delete/{trainName}")
    public ModelAndView deleteStation(@PathVariable("trainName") String trainName,
                                      ModelAndView modelAndView) {
        try {
            trainService.delete(trainName);
            modelAndView.setViewName("redirect:/train");
        } catch (DoesntExistServiceException e) {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @GetMapping(path = "/{trainName}")
    public ModelAndView getTrain(@PathVariable("trainName") String trainName,
                                 ModelAndView modelAndView) {
        modelAndView.setViewName("trainbyname");
        try {
            modelAndView.addObject("trainName", trainName);
            modelAndView.addObject("schedules", scheduleService.getByTrain(trainName));
        } catch (DoesntExistServiceException | BusinessLogicException e) {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }


    @GetMapping(path = "/updatecarriage/{carriageId}")
    public ModelAndView updateStation(@PathVariable("carriageId") Long carriageId) {
        Carriage carriage = carriageService.getById(carriageId);
        return new ModelAndView("updatecarriage", "carriageUpdateForm", carriage);
    }

    @PostMapping(path = "/updatecarriage")
    public ModelAndView updateStationForm(@Valid @ModelAttribute("carriageUpdateForm") Carriage carriage,
                                          BindingResult bindingResult,
                                          ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("updatecarriage");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/train");
        carriageService.update(carriage);
        return modelAndView;
    }


    @GetMapping(path = "/deletecarriage/{carriageId}")
    public ModelAndView deleteStation(@PathVariable("carriageId") Long carriageId,
                                      ModelAndView modelAndView) {
        carriageService.delete(carriageId);
        modelAndView.setViewName("redirect:/train");
        return modelAndView;
    }
}

