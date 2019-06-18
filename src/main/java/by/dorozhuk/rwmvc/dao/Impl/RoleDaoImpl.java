package by.dorozhuk.rwmvc.dao.Impl;

import by.dorozhuk.rwmvc.dao.RoleDao;
import by.dorozhuk.rwmvc.entity.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRole() {
        return (Role) sessionFactory.getCurrentSession()
                .createQuery("from Role where role =:Role")
                .setParameter("Role" , "ROLE_USER")
                .getSingleResult();
    }
}
