package practice.jpa.repository.query;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practice.jpa.dto.query.OrderQueryDto;
import practice.jpa.dto.query.OrderQueryItemDto;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    @Autowired
    private final EntityManager em;

    public List<OrderQueryDto> findAllByDirectQuery(){
        List<OrderQueryDto> result = em.createQuery("select new practice.jpa.dto.query.OrderQueryDto(o.id, c.name, o.orderDate, o.orderStatus, c.address)" +
                        " from Order o" +
                        " join o.customer c", OrderQueryDto.class)
                .getResultList();

        result.forEach(o -> {
            List<OrderQueryItemDto> orderQueryItemDtos = findOrderItems(o.getOrderId());
            o.setOrderItems(orderQueryItemDtos);
        });

        return result;
    }

    private List<OrderQueryItemDto> findOrderItems(Long orderId) {
        return em.createQuery("select new practice.jpa.dto.query.OrderQueryItemDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
                        + " from OrderItem oi"
                        + " join oi.item i"
                        + " where oi.order.id = :orderId", OrderQueryItemDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
}
