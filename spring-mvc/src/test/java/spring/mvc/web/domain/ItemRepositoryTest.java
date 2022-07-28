package spring.mvc.web.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Book bookA = new Book("bookA", 15000, 15, ItemName.BOOK);

        //when
        Item savedBook = itemRepository.save(bookA);

        //then
        long bookAId = bookA.getId();
        Item findBook = itemRepository.findById(bookAId);

        assertThat(savedBook).isEqualTo(findBook);

    }

    @Test
    void findAll() {
        //given
        Book book1 = new Book("book1", 15000, 15, ItemName.BOOK);
        Book book2 = new Book("book2", 25000, 25, ItemName.BOOK);
        Book book3 = new Book("book3", 35000, 35, ItemName.BOOK);

        itemRepository.save(book1);
        itemRepository.save(book2);
        itemRepository.save(book3);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items.size()).isEqualTo(3);
        assertThat(items).contains(book1, book2, book3);
    }

    @Test
    void update() {
        //given
        Book book1 = new Book("book1", 15000, 15, ItemName.BOOK);

        Item savedItem = itemRepository.save(book1);
        long itemId = savedItem.getId();

        //when
        Book newBook = new Book("book2", 25000, 25, ItemName.BOOK);
        itemRepository.update(itemId, newBook);

        //then
        Item findItem = itemRepository.findById(itemId);
        System.out.println("findItem = " + findItem.getId());
        /**
         * 왜 getId가 0이지?
         * save되지 않으므로 초기값인 0이 된다.
         */
//        assertThat(findItem.getId()).isEqualTo(newBook.getId());
        assertThat(findItem.getItemName()).isEqualTo(newBook.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(newBook.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(newBook.getQuantity());
    }

    @Test
    void clearStore() {
    }
}