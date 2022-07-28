package spring.mvc.web.basic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.mvc.web.domain.Book;
import spring.mvc.web.domain.ItemName;
import spring.mvc.web.domain.ItemRepository;

import javax.annotation.PostConstruct;


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
        return "basic/items";
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Book("BookA", 10000, 10, ItemName.BOOK));
        itemRepository.save(new Book("BookB", 20000, 20, ItemName.BOOK));
    }
}
