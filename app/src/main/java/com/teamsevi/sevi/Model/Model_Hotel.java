package com.teamsevi.sevi.Model;


public class Model_Hotel {
    private String hotelimage,hotelname,hoteladdress,hotelcity;

    Model_Hotel(){}
    public Model_Hotel(String hotelimage, String hotelname, String hoteladdress, String hotelcity) {
        this.hotelimage = hotelimage;
        this.hotelname = hotelname;
        this.hoteladdress = hoteladdress;
        this.hotelcity = hotelcity;
    }

    public String getHotelimage() {
        return hotelimage;
    }

    public void setHotelimage(String hotelimage) {
        this.hotelimage = hotelimage;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getHoteladdress() { return hoteladdress; }

    public void setHoteladdress(String hoteladdress) { this.hoteladdress = hoteladdress; }

    public String getHotelcity() { return hotelcity; }

    public void setHotelcity(String hotelcity) { this.hotelcity = hotelcity; }

}
