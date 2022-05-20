package kz.iitu.itse1909r.mukhitdinov.core.repository.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.StockProducts;
import kz.iitu.itse1909r.mukhitdinov.core.repository.StockProductsRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StockProductsRepositoryImpl implements StockProductsRepository {
    private final SessionFactory sessionFactory;

    public StockProductsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public StockProducts findByStockProduct(Long stockId, Long productId) {
        return (StockProducts) sessionFactory.getCurrentSession()
                .createQuery("from StockProducts where stock_id=:stockId and product_id=:productId")
                .setParameter("stockId", stockId)
                .setParameter("productId", productId)
                .uniqueResult();
    }

    @Override
    public void save(StockProducts stockProduct) {
        sessionFactory.getCurrentSession().save(stockProduct);
    }

    @Override
    public void update(StockProducts stockProduct) {
        sessionFactory.getCurrentSession().update(stockProduct);
    }

    @Override
    public void delete(StockProducts stockProduct) {
        sessionFactory.getCurrentSession().delete(stockProduct);
    }
}
