package by.dorozhuk.rwmvc.service.impl;

import by.dorozhuk.rwmvc.dao.RoleDao;
import by.dorozhuk.rwmvc.entity.Role;
import by.dorozhuk.rwmvc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public Role getRole() {
        try {
            return roleDao.getRole();
        } catch (NoResultException e) {
            return null;
        }
    }
}
