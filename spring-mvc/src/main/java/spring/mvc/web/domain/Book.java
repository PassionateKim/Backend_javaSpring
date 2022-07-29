package spring.mvc.web.domain;

import lombok.Data;

@Data
public class Book extends Item {
    private String dataType;

    public Book(String itemName, Integer price, Integer quantity, String dataType) {
        super(itemName, price, quantity);
        this.dataType = dataType;
    }


}
