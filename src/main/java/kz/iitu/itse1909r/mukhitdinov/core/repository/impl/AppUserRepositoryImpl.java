package kz.iitu.itse1909r.mukhitdinov.core.repository.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Role;
import kz.iitu.itse1909r.mukhitdinov.core.repository.AppUserRepository;
import kz.iitu.itse1909r.mukhitdinov.core.repository.RoleRepository;
import org.apache.catalina.core.ApplicationContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Repository
@Transactional
public class AppUserRepositoryImpl implements AppUserRepository {
    private final SessionFactory sessionFactory;
    private final RoleRepository roleRepository;
    private final BeanFactory beanFactory;

    public AppUserRepositoryImpl(SessionFactory sessionFactory,
                                 RoleRepository roleRepository,
                                 BeanFactory beanFactory) {
        this.sessionFactory = sessionFactory;
        this.roleRepository = roleRepository;
        this.beanFactory = beanFactory;
    }

    @Override
    public List<AppUser> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from AppUser").list();
    }

    @Override
    public AppUser findById(Long id) {
        return sessionFactory.getCurrentSession().find(AppUser.class, id);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return (AppUser) sessionFactory.getCurrentSession()
                .createQuery("from AppUser where username=:username")
                .setParameter("username", username)
                .uniqueResult();
    }

    @Override
    public void save(AppUser user) {
//        PasswordEncoder passwordEncoder = beanFactory.getBean(PasswordEncoder.class);
        AppUser newUser = AppUser
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")))
                .build();

        sessionFactory.getCurrentSession().saveOrUpdate(newUser);
    }
}
