package by.dorozhuk.rwmvc.dao.Impl;

import by.dorozhuk.rwmvc.dao.UserDao;
import by.dorozhuk.rwmvc.entity.User;
import by.dorozhuk.rwmvc.exception.UserAuthorizationException;
import by.dorozhuk.rwmvc.exception.UserDoesntExistException;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    @Override
    public User findUser(User user) throws UserAuthorizationException, UserDoesntExistException {

        List<User> users = new ArrayList<>();

        users = sessionFactory.getCurrentSession()
                .createQuery("from User where email = :email")
                .setParameter("email", user.getEmail())
                .list();

        if (users.size() > 0) {
            for (User userFromDb : users) {
                if (userFromDb.getPassword().equals(user.getPassword())) {
                    return userFromDb;
                }
            }
            throw new UserAuthorizationException("incorrect password");
        } else {
            logger.info("user email: " + user.getEmail() + " doesnt exist in db");
            throw new UserDoesntExistException("email doesnt exist");
        }
    }

    @Override
    public void saveUser(User user) {
        logger.info("save user" + user);
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void updateUser(User user) {
        logger.info("update user " + user);
//        sessionFactory.getCurrentSession().update(user);
        sessionFactory.getCurrentSession().createQuery("update User set firstName =:firstName, " +
                "lastName =:lastName, email =:email, password =:password where id =:id")
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("email", user.getEmail())
                .setParameter("password", user.getPassword())
                .setParameter("id", user.getId())
                .executeUpdate();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User u where email = :login")
                .setParameter("login", email)
                .getSingleResult();
    }
}
