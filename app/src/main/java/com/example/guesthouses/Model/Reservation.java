package com.example.guesthouses.Model;

public class Reservation {

    String guestHouseName;
    String checkInDate;
    String checkOutDate;
    String numOfAdults;
    String numOfChild;
    String numOfRooms;

    public Reservation(String guestHouseName, String checkInDate, String checkOutDate,
                       String numOfAdults, String numOfChild, String numOfRooms) {
        this.guestHouseName = guestHouseName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numOfAdults = numOfAdults;
        this.numOfChild = numOfChild;
        this.numOfRooms = numOfRooms;
    }

    public String getGuestHouseName() {
        return guestHouseName;
    }

    public void setGuestHouseName(String guestHouseName) {
        this.guestHouseName = guestHouseName;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getNumOfAdults() {
        return numOfAdults;
    }

    public void setNumOfAdults(String numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    public String getNumOfChild() {
        return numOfChild;
    }

    public void setNumOfChild(String numOfChild) {
        this.numOfChild = numOfChild;
    }

    public String getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(String numOfRooms) {
        this.numOfRooms = numOfRooms;
    }
}
