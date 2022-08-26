package practice.jpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.domain.*;
import practice.jpa.repository.OrderRepository;
import static org.junit.Assert.*;
import javax.persistence.EntityManager;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Customer customer = createCustomer();
        Phone phone = createPhone("갤럭시 노트10", 100000, 20);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(customer.getId(), phone.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 상태", OrderStatus.ORDER,getOrder.getOrderStatus());
    }

    @Test(expected = IllegalStateException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Customer customer = createCustomer();
        Phone phone = createPhone("갤럭시 노트10", 100000, 20);

        int orderCount = 21;

        //when
        orderService.order(customer.getId(), phone.getId(), orderCount);

        //then
        fail("재고 수량 예외가 발생해야 합니다.");
    }

    @Test
    public void 주문취소() {
        //given
        Customer customer = createCustomer();
        Phone phone = createPhone("갤럭시 노트10", 100000, 20);

        int orderCount = 11;
        Long orderId = orderService.order(customer.getId(), phone.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);


        assertEquals("남은 재고 수량",20, phone.getStockQuantity());
        assertEquals("주문 취소시 상태", OrderStatus.CANCEL, getOrder.getOrderStatus());
    }

    private Phone createPhone(String name, int price, int stockQuantity) {
        Phone phone = new Phone();
        phone.setName(name);
        phone.setPrice(price);
        phone.setStockQuantity(stockQuantity);
        phone.setCompany("삼성");
        em.persist(phone);

        return phone;
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setName("kim");
        customer.setAddress(new Address("충청북도","마리동","15대로"));
        em.persist(customer);

        return customer;
    }
}
