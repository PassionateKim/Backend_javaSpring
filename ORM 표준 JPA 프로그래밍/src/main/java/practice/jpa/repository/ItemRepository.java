package practice.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practice.jpa.domain.Customer;
import practice.jpa.domain.Item;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @Autowired
    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }

    public List<Item> findAll(){
        return em.createQuery("select m from Item m", Item.class)
                .getResultList();
    }

    public Item findOne(Long customerId){
        return em.find(Item.class, customerId);
    }

}
