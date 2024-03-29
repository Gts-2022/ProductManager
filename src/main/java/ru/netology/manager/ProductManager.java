package ru.netology.manager;

import ru.netology.domain.*;
import ru.netology.repository.ProductRepository;

public class ProductManager {
    ProductRepository repository;


    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void save(Product product) {
        repository.save(product);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0]; // тут будем хранить подошедшие запросу продукты
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
  /*              for (int i = 0; i < result.length; i++) {
                    tmp[i]=result[i];

                }*/
                System.arraycopy(result, 0, tmp, 0, result.length);//Заменяет 2 строчки выше
                tmp[tmp.length - 1] = product;
                result = tmp;
            }

        }
        return result;
    }

    // метод определения соответствия товара product запросу search
    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
        // или в одну строку:
        // return product.getName().contains(search);
    }

}
