package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exception.NotFoundException;


public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product product) {//Сохранять продукт
        Product[] tmp = new Product[products.length + 1];//Создаем новый массив
        System.arraycopy(products, 0, tmp, 0, products.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = product;

        products = tmp;

    }

    public Product[] findAll() {
        return products;

    }

    public void removeById(int id) {//Удалять по ID
        Product[] remove = findById(id);// Ищем товар ,который нужно удалить, используя метод findById
        if (remove == null) {//Если товар не находится , то подключаем исключение otFoundException
            throw new NotFoundException(id);
        }
        int length = products.length - 1;
        Product[] tmp = new Product[length];
        int copyToIndex = 0; //Переменная для сохоанения места, куда будет сохранятся новый массив
        for (Product product : products) {//Проходимся по всем продуктам, которые сохранены в новом массиве
            if (product.getId() != id) { //Если у прдукта не такое id, которое хотим удалить
                tmp[copyToIndex] = product;//То мы его копируем в новый массив
                copyToIndex++;

            }
        }
        products = tmp;//Сохраняем новый массив в нашем поле
    }

    public Product[] findById(int id) {//метод findById, предназначенный для поиска товара в репозитории по его ID

        for (Product product : products) {// Перебираем все товары ,которые хранятся в репозитории
            if (product.getId() == id) {
                return products;
            }
        }
        return null;
    }

    public Product[] getProducts() {//Возвращает все Продукты в виде массива
        return products;
    }

}
