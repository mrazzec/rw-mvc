package by.dorozhuk.rwmvc.service.impl;

import by.dorozhuk.rwmvc.dao.Impl.UserDaoImpl;
import by.dorozhuk.rwmvc.entity.User;
import by.dorozhuk.rwmvc.exception.UserAuthorizationException;
import by.dorozhuk.rwmvc.exception.UserAuthorizationServiceException;
import by.dorozhuk.rwmvc.exception.UserDoesntExistException;
import by.dorozhuk.rwmvc.exception.DoesntExistServiceException;
import by.dorozhuk.rwmvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public boolean findUser(User user, HttpServletRequest request) throws UserAuthorizationServiceException, DoesntExistServiceException {
        try {
            User currentUser = userDao.findUser(user);
            request.getSession().setAttribute("user", currentUser);
            logger.info(currentUser + "found and added in session");
            return true;
        } catch (UserAuthorizationException e) {
            throw new UserAuthorizationServiceException(e.getMessage());
        } catch (UserDoesntExistException e) {
            throw new DoesntExistServiceException(e.getMessage());
        }
    }

    @Override
    public void saveUser(User user) throws DoesntExistServiceException {

        if (findUserByEmail(user.getEmail()) != null)
            throw new DoesntExistServiceException("Someone already has that login");

        user.setRole(roleService.getRole());
        userDao.saveUser(user);

        logger.info(user + " saved");
    }

    @Override
    public void deleteUser(User user) {
    }

    @Override
    public void updateUser(User user, HttpServletRequest request) {
        User userInSession = (User) request.getSession().getAttribute("user");
        user.setRole(userInSession.getRole());
        userDao.updateUser(user);
        request.getSession().setAttribute("user", user);
        logger.info(user + " updated");
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            return userDao.findUserByEmail(email);
        } catch (NoResultException e) {
            return null;
        }
    }

    private User md5User(User user) {
        String s = DigestUtils.md5DigestAsHex((user.getEmail() + user.getPassword()).getBytes());
        user.setPassword(s);
        return user;
    }
}
