package by.dorozhuk.rwmvc.controller;

import by.dorozhuk.rwmvc.entity.User;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;
import by.dorozhuk.rwmvc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/reg")
public class RegController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ModelAndView reg() {
        return new ModelAndView("registration", "regFormUser", new User());
    }

    @PostMapping
    public ModelAndView regFormUser(@Valid @ModelAttribute("regFormUser") User user,
                                    BindingResult bindingResult,
                                    ModelAndView modelAndView) {

        modelAndView.setViewName("registration");
        try {
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("registration");
                return modelAndView;
            }
            userService.saveUser(user);
            modelAndView.setViewName("redirect:/");
        } catch (DoesntExistServiceException e) {
            bindingResult.rejectValue("email", "error.user", e.getMessage());
        }
        return modelAndView;
    }
}
