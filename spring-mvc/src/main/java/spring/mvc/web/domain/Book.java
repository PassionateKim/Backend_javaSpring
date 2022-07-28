package spring.mvc.web.domain;

import lombok.Data;

@Data
public class Book extends Item {
    private String datatype;

    public Book(String itemName, Integer price, Integer quantity, String datatype) {
        super(itemName, price, quantity);
        this.datatype = datatype;
    }


}
