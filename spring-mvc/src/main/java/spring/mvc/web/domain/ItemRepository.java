package spring.mvc.web.domain;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRepository {

    private static long sequence = 0L;
    private static Map<Long, Item> database = new HashMap<>();

    public Item save(Item item) {
        item.setId(++sequence);
        database.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        Item findItem = database.get(id);
        return findItem;
    }

    public List<Item> findAll() {
        Collection<Item> values = database.values();
        return new ArrayList<> (values);
    }

    /**
     * set만 해주면 끝인가? 저장은 다로 안해도?? ㅇㅇ
     * 객체는 참조변수잖아
     */
    public void update(long id, Item item) {
        Item findItem = database.get(id);
        findItem.setItemName(item.getItemName());
        findItem.setPrice(item.getPrice());
        findItem.setQuantity(item.getQuantity());

    }

    public void clearStore() {
        database.clear();
    }
}
