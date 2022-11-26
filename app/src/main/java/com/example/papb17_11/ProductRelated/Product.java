package com.example.papb17_11.ProductRelated;

public class Product {
    private int id;
    private String name;
    private String desc;
    private int price;
    private String brand;

    public Product() {
    }

    public Product(String name, int price, String brand, String desc) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.desc = desc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
