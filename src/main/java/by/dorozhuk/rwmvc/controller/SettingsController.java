package by.dorozhuk.rwmvc.controller;

import by.dorozhuk.rwmvc.entity.User;
import by.dorozhuk.rwmvc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/settings")
public class SettingsController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ModelAndView settings(ModelAndView modelAndView, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        modelAndView.setViewName("settings");
        modelAndView.addObject("changeUser", user);
        System.out.println(user.getRole());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView changeUser(@Valid @ModelAttribute("changeUser") User user, BindingResult bindingResult,
                                   ModelAndView modelAndView,
                                   HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("settings");
        }
        userService.updateUser(user, request);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
