package com.example.guesthouses.Model;

public class GuestHouse {

    private String name;
    private String price;
    private String location;
    private String rating;
    private String imageUrl;
    private String phone;
    private String description;
    private String houseId;

    public GuestHouse(String houseId, String name, String price, String location, String rating,
                      String imageUrl, String phone, String description) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.phone = phone;
        this.description = description;
        this.houseId = houseId;
    }

    public GuestHouse() {
    }

    public String getName() {
        return name;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
