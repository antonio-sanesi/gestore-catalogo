package dev.anto.gestore.catalogo.entity;

@Entity
public class Product {

    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private string description;

    @Column(name="price")
    private double price;
}
