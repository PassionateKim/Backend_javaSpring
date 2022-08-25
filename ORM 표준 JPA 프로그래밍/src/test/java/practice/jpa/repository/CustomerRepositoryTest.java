package practice.jpa.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.domain.Customer;
import practice.jpa.service.CustomerService;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerRepositoryTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        //given
        Customer customer = new Customer();
        customer.setName("김고라니");

        //when
        Long id = customerService.join(customer);
        Customer foundCustomer = customerService.findOne(id);

        em.flush();

        //then
        Assertions.assertEquals(customer, foundCustomer);

    }

    @Test(expected = IllegalStateException.class)
    public void 중복예외() throws Exception{
        //given
        Customer customer = new Customer();
        customer.setName("김고라니");

        Customer customer2 = new Customer();
        customer2.setName(customer.getName());

        //when
        customerService.join(customer);
        customerService.join(customer2);

        //then
        fail("예외가 발생해야 합니다.");

    }
}