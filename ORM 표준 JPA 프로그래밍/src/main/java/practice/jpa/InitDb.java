package practice.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.domain.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * userA
 *  *JPA1 Book
 *  *JPA2 Book
 */
@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1(){
            Customer customer = createCustomer("userA", "충청도", "팔동", "1111");
            em.persist(customer);

            Phone phone1 = createPhone("galaxy note 10 +", 100000, 100,"SAMSUNG");
            em.persist(phone1);

            Phone phone2 = createPhone("galaxy note 20 +", 150000, 100,"SAMSUNG");
            em.persist(phone2);

            OrderItem orderItem1 = OrderItem.createOrderItem(phone1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(phone2, 20000, 2);

            Order order = Order.createOrder(customer, orderItem1, orderItem2);
            em.persist(order);

        }

        public void dbInit2(){
            Customer customer = createCustomer("userB", "함경도", "팔동", "1111");
            em.persist(customer);

            Phone phone1 = createPhone("Iphone 10 +", 200000, 100, "APPLE");
            em.persist(phone1);

            Phone phone2 = createPhone("Iphone 20 +", 250000, 100,"APPLE");
            em.persist(phone2);

            OrderItem orderItem1 = OrderItem.createOrderItem(phone1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(phone2, 20000, 2);

            Order order = Order.createOrder(customer, orderItem1, orderItem2);
            em.persist(order);
        }


        private Phone createPhone(String name, int price, int stockQuantity, String company) {
            Phone phone1 = new Phone();
            phone1.setName(name);
            phone1.setPrice(price);
            phone1.setStockQuantity(stockQuantity);
            phone1.setCompany(company);

            return phone1;
        }

        private Customer createCustomer(String name, String palDo, String dong, String street) {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setAddress(new Address(palDo, dong, street));
            return customer;
        }
    }
}


