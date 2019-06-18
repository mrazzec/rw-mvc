package by.dorozhuk.rwmvc.dao;

import by.dorozhuk.rwmvc.entity.User;
import by.dorozhuk.rwmvc.exception.UserAuthorizationException;
import by.dorozhuk.rwmvc.exception.UserDoesntExistException;

import java.util.List;

public interface UserDao {

    User findUser(User user) throws UserAuthorizationException, UserDoesntExistException;

    void saveUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    List<User> findAll();

    User findUserByEmail(String email);
}
