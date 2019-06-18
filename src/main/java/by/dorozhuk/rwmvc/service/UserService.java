package by.dorozhuk.rwmvc.service;

import by.dorozhuk.rwmvc.entity.User;
import by.dorozhuk.rwmvc.exception.UserAuthorizationServiceException;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    boolean findUser(User user, HttpServletRequest request) throws UserAuthorizationServiceException, DoesntExistServiceException;

    void saveUser(User user) throws DoesntExistServiceException;

    void deleteUser(User user);

    void updateUser(User user, HttpServletRequest request);

    List<User> findAll();

    User findUserByEmail(String email);
}
