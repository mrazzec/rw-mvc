package by.dorozhuk.rwmvc.controller;

import by.dorozhuk.rwmvc.entity.User;
import by.dorozhuk.rwmvc.exception.UserAuthorizationServiceException;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ModelAndView login() {
        return new ModelAndView("login", "logUser", new User());
    }

    @PostMapping
    public ModelAndView loginForm(@Valid @ModelAttribute("logUser") User user,
                                  BindingResult bindingResult,
                                  ModelAndView modelAndView,
                                  HttpServletRequest request) {
        modelAndView.setViewName("login");
        try {
            if (userService.findUser(user, request)) {
                modelAndView.setViewName("redirect:/");
            }
        } catch (DoesntExistServiceException e) {
            bindingResult.rejectValue("email", "error.user", e.getMessage());
        }catch (UserAuthorizationServiceException e) {
            bindingResult.rejectValue("password", "error.user", e.getMessage());
        }
        return modelAndView;
    }
}
