package kz.iitu.itse1909r.mukhitdinov.core.repository.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Role;
import kz.iitu.itse1909r.mukhitdinov.core.repository.RoleRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {
    SessionFactory sessionFactory;

    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Role> findAll() {
//        return null;
        return sessionFactory.getCurrentSession().createQuery("from Role").list();
    }

    @Override
    public Role findByName(String name) {
//        return null;
        return (Role) sessionFactory.getCurrentSession()
                .createQuery("from Role where name=:name")
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public void save(Role role) {
        sessionFactory.getCurrentSession().saveOrUpdate(role);
    }
}
