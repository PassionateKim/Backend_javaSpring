package practice.jpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import practice.jpa.domain.Item;
import practice.jpa.domain.Phone;
import practice.jpa.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createPhoneForm(Model model){
        PhoneForm phoneForm = new PhoneForm();
        model.addAttribute("phoneForm", phoneForm);

        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String phoneForm(@Valid PhoneForm phoneForm, BindingResult result){
        if(result.hasErrors()){
            return "items/createItemForm";
        }
        // 생성자를 통한 설계
        Phone phone = new Phone(phoneForm.getName(),
                phoneForm.getPrice(),
                phoneForm.getStockQuantity(),
                phoneForm.getCompany());

        itemService.saveItem(phone);

        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Phone phone = (Phone)itemService.findOne(itemId);
        PhoneForm phoneForm = new PhoneForm();
        phoneForm.setId(phone.getId());
        phoneForm.setName(phone.getName());
        phoneForm.setPrice(phone.getPrice());
        phoneForm.setStockQuantity(phone.getStockQuantity());
        phoneForm.setCompany(phone.getCompany());

        model.addAttribute("form", phoneForm);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") @Valid PhoneForm form, BindingResult result){
        if(result.hasErrors()){
            return "items/updateItemForm";
        }

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity(), form.getCompany());

        return "redirect:/items";
    }
}
