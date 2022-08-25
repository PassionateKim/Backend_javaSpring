package practice.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practice.jpa.domain.Customer;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    @Autowired
    private final EntityManager em;

    public void save(Customer customer){
        em.persist(customer);
    }

    public Customer findOne(Long id){
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll(){
        return em.createQuery("select m from Customer m", Customer.class)
                .getResultList();
    }

    public List<Customer> findByName(String name){
        return em.createQuery("select m from Customer m where m.name = :name", Customer.class)
                .setParameter("name", name)
                .getResultList();
    }
}
