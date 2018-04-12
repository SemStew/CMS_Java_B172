package com.vaadin.starter.SemStew.backend;


import java.io.Serializable;

public class MenuItem implements Serializable {
    private Long id = null;
    private int price;
    private String name;
    private String description;
    private Category category;

    public MenuItem()
    {
        this.reset();
    }

    public MenuItem(int price, String name, String description, Category category)
    {
        this.price = price;
        this.name = name;
        this.description = description;
        this.category = new Category(category);
    }

    public MenuItem(MenuItem other)
    {
        this(other.price,other.name,other.description,other.category);
        this.id = other.id;
    }

    private void reset()
    {
        this.id = null;
        this.price = 0;
        this.name = "";
        this.description = "";
        this.category = null;
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
