package ru.netology.domain;
public class Book extends Product {
    private String author;//Автор

    public Book(int id, String name, int price,String author) {
        super(id, name, price);
        this.author=author;
    }
}
