package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceLayer {
    RepositoryLayer repositoryLayer = new RepositoryLayer();
    private HashMap<String, Hotel> hotelMap = repositoryLayer.getHotelMap();
    private HashMap<String, User> userMap = repositoryLayer.getUserMap();
    private HashMap<String, Booking> bookingMap = repositoryLayer.getBookingMap();

    public String addHotel(Hotel hotel)
    {
        if (hotel == null) {
            return "FAILURE";
        }
        if (hotel.getHotelName() == null) {
            return "FAILURE";
        }
        if(hotelMap.containsKey(hotel.getHotelName()))
            return "FAILURE";

        hotelMap.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
    }

    public int addUser (User user)
    {
        userMap.put(user.getName(),user);
        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities()
    {
        String ans = "";
        int res =0;
        for(Hotel hotel: hotelMap.values())
        {
            if(hotel.getFacilities().size()>res) {
                res = hotel.getFacilities().size();
                ans = hotel.getHotelName();
            }
        }
        return ans;
    }

    public int bookRoom(Booking booking)
    {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        int rent =0;

        for(Hotel hotel: hotelMap.values())
        {
            if(hotelMap.get(booking.getHotelName()).getAvailableRooms()<booking.getNoOfRooms()){
                return -1;
            }
            if(hotel.getHotelName().equals(booking.getHotelName()))
            {
                rent = hotel.getPricePerNight();
            }
        }
        int price = booking.getNoOfRooms()*rent;
        booking.setAmountToBePaid(price);
        bookingMap.put(uuid,booking);
        return booking.getAmountToBePaid();
    }

    public int getBookings(Integer aadhar)
    {
        int ans =0;
        for(Booking booking: bookingMap.values())
        {
            if(booking.getBookingAadharCard()==aadhar)
                ans = ans + booking.getNoOfRooms();
        }
        return ans;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName)
    {
        List<Facility> res = new ArrayList<>();
        for(Hotel hotel: hotelMap.values())
        {
            if(hotel.getHotelName().equals(hotelName))
            {
                List<Facility> fac = hotel.getFacilities();
                for (Facility f : newFacilities) {
                    if (!fac.contains(f))
                        res.add(f);
                }
            }
            hotel.setFacilities(res);
        }
        Hotel hotel = hotelMap.get(hotelName);
        return hotel;
    }
}
