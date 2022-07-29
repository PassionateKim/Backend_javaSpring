package spring.mvc.web.basic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.mvc.web.domain.Book;
import spring.mvc.web.domain.Item;
import spring.mvc.web.domain.ItemName;
import spring.mvc.web.domain.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
@RequestMapping("/basic/items")
public class BasicItemController {

    private ItemRepository itemRepository;

    @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    @GetMapping("{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item foundItem = itemRepository.findById(itemId);
        model.addAttribute("item", foundItem);

        return "basic/item";
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Book("BookA", 10000, 10, ItemName.BOOK));
        itemRepository.save(new Book("BookB", 20000, 20, ItemName.BOOK));
        itemRepository.save(new Book("BookC", 30000, 30, ItemName.BOOK));
        itemRepository.save(new Book("BookD", 40000, 40, ItemName.BOOK));


    }
}
