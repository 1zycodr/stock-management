package kz.iitu.itse1909r.mukhitdinov.core.repository.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.PurchaseProducts;
import kz.iitu.itse1909r.mukhitdinov.core.repository.PurchaseProductsRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PurchaseProductsRepositoryImpl implements PurchaseProductsRepository {
    private final SessionFactory sessionFactory;

    public PurchaseProductsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PurchaseProducts findByPurchaseProduct(Long purchaseId, Long productId) {
        return (PurchaseProducts) sessionFactory.getCurrentSession()
                .createQuery("from PurchaseProducts where purchase_id=:purId and product_id=:prodId")
                .setParameter("purId", purchaseId)
                .setParameter("prodId", productId)
                .uniqueResult();
    }

    @Override
    public void update(PurchaseProducts purchaseProducts) {
        sessionFactory.getCurrentSession().update(purchaseProducts);
    }

    @Override
    public void save(PurchaseProducts purchaseProducts) {
        sessionFactory.getCurrentSession().save(purchaseProducts);
    }

    @Override
    public void delete(PurchaseProducts purchaseProducts) {
        sessionFactory.getCurrentSession().delete(purchaseProducts);
    }
}
