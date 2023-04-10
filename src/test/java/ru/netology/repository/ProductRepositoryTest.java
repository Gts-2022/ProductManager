package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.netology.domain.Product;
import ru.netology.exception.NotFoundException;
import ru.netology.manager.ProductManager;


public class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    //  ProductManager manager = new ProductManager(repository);
    Product product1 = new Product(1, "1984", 100);
    Product product2 = new Product(2, "Колобок", 200);
    Product product3 = new Product(3, "Honor", 5000);

    @Test //Тест 1 (успешное удаление существующего элемента)
    public void successfullyDeletingExistingItem() {
        repository.save(product1);
        repository.save(product2);
        repository.save(product3);
        repository.removeById(1);
        Product[] expected = {product2, product3};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //Тест 2(Генерация NotFoundException при попытке удаления несуществующего элемента)
    public void mustDeleteNegativeId() {
        repository.save(product1);
        repository.save(product2);
        repository.save(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(100);
        });
    }


    @Test
    public void shouldSaveOneProduct() {
        repository.save(product1);
        repository.save(product2);

        Product[] expected = new Product[]{product1, product2};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveId() {
        repository.save(product1);
        repository.save(product2);
        repository.save(product3);
        repository.removeById(product2.getId());
        Product[] expected = {product1, product3};
        Product[] actual = repository.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

}
