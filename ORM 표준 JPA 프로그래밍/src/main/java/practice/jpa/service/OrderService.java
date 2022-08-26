package practice.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.domain.Customer;
import practice.jpa.domain.Item;
import practice.jpa.domain.Order;
import practice.jpa.domain.OrderItem;
import practice.jpa.repository.CustomerRepository;
import practice.jpa.repository.ItemRepository;
import practice.jpa.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final ItemRepository itemRepository;

    /**주문하기*/
    @Transactional
    public Long order(Long customerId, Long itemId, int count){
        Customer customer = customerRepository.findOne(customerId);
        Item item = itemRepository.findOne(itemId);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(customer, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    /**취소하기*/
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
