package com.example.guesthouses.Model;

public class HousePreviewImages {

    String guestHouseName;
    String imageUrl;

    public HousePreviewImages(String guestHouseName, String imageUrl) {
        this.guestHouseName = guestHouseName;
        this.imageUrl = imageUrl;
    }

    public String getGuestHouseName() {
        return guestHouseName;
    }

    public void setGuestHouseName(String guestHouseName) {
        this.guestHouseName = guestHouseName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
