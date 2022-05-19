package kz.iitu.itse1909r.mukhitdinov.core.repository.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.Privilege;
import kz.iitu.itse1909r.mukhitdinov.core.repository.PrivilegeRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PrivilegeRepositoryImpl implements PrivilegeRepository {
    SessionFactory sessionFactory;

    public PrivilegeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Privilege findPrivilegeByName(String name) {
        return (Privilege) sessionFactory.getCurrentSession()
                .createQuery("from Privilege where name=:name")
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public void save(Privilege privilege) {
        sessionFactory.getCurrentSession().saveOrUpdate(privilege);
    }
}
