package com.synopsis.mc_api.mc_api.models;


import java.util.List;

public class ExternalProductBean {
    private String title;
    private Double price;
    private String description;
    private List<String> images;

    public ExternalProductBean(String title, Double price, String description, List<String> images) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
