package kz.iitu.itse1909r.mukhitdinov.core.repository.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.repository.AppUserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class AppUserRepositoryImpl implements AppUserRepository {
//    SessionFactory sessionFactory;
//
//    public AppUserRepositoryImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    public List<AppUser> findAll() {
//        return sessionFactory.getCurrentSession().createQuery("from AppUser").list();
//    }
}
