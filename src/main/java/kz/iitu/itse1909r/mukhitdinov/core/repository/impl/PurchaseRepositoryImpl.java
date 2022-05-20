package kz.iitu.itse1909r.mukhitdinov.core.repository.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.Purchase;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;
import kz.iitu.itse1909r.mukhitdinov.core.repository.PurchaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PurchaseRepositoryImpl implements PurchaseRepository {
    private final SessionFactory sessionFactory;

    public PurchaseRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Purchase> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Purchase").list();
    }

    @Override
    public Purchase findById(Long id) {
        return sessionFactory.getCurrentSession().find(Purchase.class, id);
    }

    @Override
    public Purchase save(Purchase purchase) {
        sessionFactory.getCurrentSession().save(purchase);
        return purchase;
    }

    @Override
    public Purchase update(Purchase purchase) {
        sessionFactory.getCurrentSession().update(purchase);
        return purchase;
    }

    @Override
    public void delete(Purchase purchase) {
        sessionFactory.getCurrentSession().delete(purchase);
    }
}
