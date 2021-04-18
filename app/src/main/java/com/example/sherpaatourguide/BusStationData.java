package com.example.sherpaatourguide;

public class BusStationData {
    private String BusStationname;
    private String BusDescription;
    private String BusStationLocation;
    private String BusStationPhone;
    private String BusImageId;

    public BusStationData() {

    }

    public BusStationData(String busStationname, String busDescription, String busStationLocation, String busStationPhone, String imageId) {
        BusStationname = busStationname;
        BusDescription = busDescription;
        BusStationLocation = busStationLocation;
        BusStationPhone = busStationPhone;
        BusImageId = imageId;
    }

    public String getBusStationname() {
        return BusStationname;
    }

    public void setBusStationname(String busStationname) {
        BusStationname = busStationname;
    }

    public String getBusDescription() {
        return BusDescription;
    }

    public void setBusDescription(String busDescription) {
        BusDescription = busDescription;
    }

    public String getBusStationLocation() {
        return BusStationLocation;
    }

    public void setBusStationLocation(String busStationLocation) {
        BusStationLocation = busStationLocation;
    }

    public String getBusStationPhone() {
        return BusStationPhone;
    }

    public void setBusStationPhone(String busStationPhone) {
        BusStationPhone = busStationPhone;
    }


    public String getImageId() {
        return BusImageId;
    }

    public void setImageId(String imageId) {
        BusImageId = imageId;
    }
}