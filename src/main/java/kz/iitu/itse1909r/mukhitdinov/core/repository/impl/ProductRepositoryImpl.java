package kz.iitu.itse1909r.mukhitdinov.core.repository.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.Product;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;
import kz.iitu.itse1909r.mukhitdinov.core.repository.ProductRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    private final SessionFactory sessionFactory;

    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Product").list();
    }

    @Override
    public Product findById(Long id) {
        return sessionFactory.getCurrentSession().find(Product.class, id);
    }

    @Override
    public Product save(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        sessionFactory.getCurrentSession().update(product);
        return product;
    }

    @Override
    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }
}
