package spring.mvc.web.basic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.mvc.web.domain.Book;
import spring.mvc.web.domain.Item;
import spring.mvc.web.domain.ItemName;
import spring.mvc.web.domain.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
public class BasicItemController {

    private ItemRepository itemRepository;

    @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * 상품 목록
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    /**
     * 상품 상세
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item foundItem = itemRepository.findById(itemId);
        model.addAttribute("item", foundItem);

        return "basic/item";
    }

    /**
     * 상품 등록
     */
    @GetMapping("/add")
    public String addForm() {

        return "basic/addForm";
    }

    /**
     * 라벨이랑 도메인 필드랑 똑같아야함.
     */
//    @PostMapping("/add")
//    public String addItem(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
//        Book savedItem = (Book) itemRepository.save(book);
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        log.info("savedItem.quantity={}",savedItem.getQuantity());
//        log.info("savedItem.price={}",savedItem.getPrice());
//        return "redirect:/basic/items/{itemId}";
//    }

    @PostMapping("/add")
    public String addItem(@RequestParam("itemName") String itemName,
                          @RequestParam("price") Integer itemPrice,
                          @RequestParam("quantity") Integer itemQuantity,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        Book book = new Book(itemName, itemPrice, itemQuantity, ItemName.BOOK);
        Item savedItem = itemRepository.save(book);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") long id, Model model) {
        Item foundItem = itemRepository.findById(id);

        model.addAttribute("item", foundItem);
        return "basic/editForm";
    }

    /**
     * 상품 수정
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") long itemId,
                       @RequestParam("itemName") String itemName,
                       @RequestParam("price") Integer itemPrice,
                       @RequestParam("quantity") Integer itemQuantity,
                       Model model) {


        Book book = new Book(itemName, itemPrice, itemQuantity, ItemName.BOOK);

        itemRepository.update(itemId, book);

        return "redirect:/basic/items/{itemId}";
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
