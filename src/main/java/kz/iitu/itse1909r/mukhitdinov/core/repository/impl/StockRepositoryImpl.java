package kz.iitu.itse1909r.mukhitdinov.core.repository.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;
import kz.iitu.itse1909r.mukhitdinov.core.repository.StockRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StockRepositoryImpl implements StockRepository {
    private final SessionFactory sessionFactory;

    public StockRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Stock> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Stock").list();
    }

    @Override
    public Stock findById(Long id) {
        return sessionFactory.getCurrentSession().find(Stock.class, id);
    }

    @Override
    public Stock save(Stock stock) {
        sessionFactory.getCurrentSession().save(stock);
        return stock;
    }

    @Override
    public Stock update(Stock stock) {
        sessionFactory.getCurrentSession().update(stock);
        return stock;
    }

    @Override
    public void delete(Stock stock) {
        sessionFactory.getCurrentSession().delete(stock);
    }
}
