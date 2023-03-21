package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;
import ru.netology.repository.ProductRepository;

import javax.lang.model.element.Name;
import java.util.Arrays;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product product1 = new Product(1, "1984", 100);
    Product product2 = new Product(2, "Колобок", 200);
    Product product3 = new Product(3, "BQQ", 5000);
    Product product4 = new Book(1, "1984", 100, "Пушкин");
    Product product5 = new Smartphone(5, "Honor", 1500, "USA");
    Product product6 = new Product(6, "Колобок", 300);

    @BeforeEach
    public void setup() {
        manager.save(product1);
        manager.save(product2);
        manager.save(product3);
        manager.save(product4);
        manager.save(product5);
        manager.save(product6);
    }

    @Test
    public void shouldFindByName() {//Найти 1 продукт по названию
        String name = "BQQ";
        Product[] expected = new Product[]{product3};
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldProductDoesNotExit() {//Найти несуществующий продукт
        String nameToSearch = "ГОРОД";
        Product[] expected = {};
        Product[] actual = manager.searchBy(nameToSearch);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByAuthor() {
        String author = "Пушкин";
        Product[] expected = new Book[]{};
        Product[] actual = manager.searchBy(author);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindByManufacturer() {
        String manufacturer = "USA";
        Product[] expected = new Smartphone[]{};
        Product[] actual = manager.searchBy(manufacturer);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void NotOneProductDoesNotSuitable() {//Не один товар не подходит
        String NotSuitable = "Финансист";
        Product[] expected = {};
        Product[] actual = manager.searchBy(NotSuitable);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindSeveralProducts() {//Несколько товаров  с одинаковым названием (цена, id разные)
        String Name = "Колобок";
        Product[] expected = new Product[]{product2, product6};
        Product[] actual = manager.searchBy(Name);
        Assertions.assertArrayEquals(expected, actual);

    }


}