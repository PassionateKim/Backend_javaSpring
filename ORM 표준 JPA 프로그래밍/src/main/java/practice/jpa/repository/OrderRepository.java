package practice.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practice.jpa.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    @Autowired
    private final EntityManager em;

    /**
     * 저장
     */
    public void save(Order order){
        em.persist(order);
    }

    /**
     * 찾기
     */
    public Order findOne(Long id){
        Order order = em.find(Order.class, id);
        return order;
    }

    /**
     * 동적 쿼리해야하는 부분..일단 pass
     */
    public List<Order> findAll(){
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public List<Order> findAllByFetchJoin() {
        return em.createQuery("select o from Order o"
        + " join fetch o.customer c"
        , Order.class).getResultList();
    }

    public List<Order> findAllByFetchOrders() {
        return em.createQuery("select distinct o from Order o" +
                            " join fetch o.customer c" +
                            " join fetch o.orderItems oi" +
                            " join fetch oi.item i", Order.class)
                .getResultList();
    }

    public List<Order> findAllWithToOne(int offset, int limit) {
        return em.createQuery("select o from Order o" +
                            " join fetch o.customer c", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
