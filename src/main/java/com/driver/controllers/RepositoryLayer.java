package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
@Repository
public class RepositoryLayer {
    private HashMap<String, Hotel> hotelMap = new HashMap<>();
    private HashMap<String, User> userMap = new HashMap<>();
    private HashMap<String , Booking> bookingMap = new HashMap<>();

    public HashMap<String, Hotel> getHotelMap() {
        return hotelMap;
    }

    public void setHotelMap(HashMap<String, Hotel> hotelMap) {
        this.hotelMap = hotelMap;
    }

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }

    public HashMap<String, Booking> getBookingMap() {
        return bookingMap;
    }

    public void setBookingMap(HashMap<String, Booking> bookingMap) {
        this.bookingMap = bookingMap;
    }
}
