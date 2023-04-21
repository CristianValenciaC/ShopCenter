package com.rob.shopcenter.clases;

public class Product {

    private int idProduct;
    private static int numProduct = 1;
    private int url_photo;
    private String title;
    private String description;
    private int numStock;
    private double price;

    public Product(int url_photo, String title, String description, int numStock, double price) {
        this.idProduct=numProduct++;
        this.url_photo = url_photo;
        this.title = title;
        this.description = description;
        this.numStock = numStock;
        this.price = price;
    }

    public int getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(int url_photo) {
        this.url_photo = url_photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumStock() {
        return numStock;
    }

    public void setNumStock(int numStock) {
        this.numStock = numStock;
    }

    public void sumProduct(int num){
        numStock += num;
    }

    public void restProduct(int num){
        numStock -= num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
