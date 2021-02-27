package com.example.sherpaatourguide;

public class BusStationData {
    private String BusStationname;
    private String BusDescription;
    private String BusStationLocation;
    private String BusStationPhone;
    private String ImageId;

    public BusStationData() {

    }

    public BusStationData(String busStationname, String busDescription, String busStationLocation, String busStationPhone, String imageId) {
        BusStationname = busStationname;
        BusDescription = busDescription;
        BusStationLocation = busStationLocation;
        BusStationPhone = busStationPhone;
        ImageId = imageId;
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
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }
}