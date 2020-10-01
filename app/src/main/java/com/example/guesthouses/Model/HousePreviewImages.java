package com.example.guesthouses.Model;

public class HousePreviewImages {

    String guestHouseId;
    String imageUrl;

    public HousePreviewImages(String guestHouseName, String imageUrl) {
        this.guestHouseId = guestHouseName;
        this.imageUrl = imageUrl;
    }

    public HousePreviewImages() {
    }

    public String getGuestHouseId() {
        return guestHouseId;
    }

    public void setGuestHouseId(String guestHouseId) {
        this.guestHouseId = guestHouseId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
