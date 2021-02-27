package com.example.sherpaatourguide.activity.ui;

public class DestinationData {
    private String dName;
    private String dDescription;
    private String dLocation;

    private String dImageId;

    public DestinationData() {

    }

    public DestinationData(String dName, String dDescription, String dLocation, String dImageId) {
        this.dName = dName;
        this.dDescription = dDescription;
        this.dLocation = dLocation;
        this.dImageId = dImageId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdDescription() {
        return dDescription;
    }

    public void setdDescription(String dDescription) {
        this.dDescription = dDescription;
    }

    public String getdLocation() {
        return dLocation;
    }

    public void setdLocation(String dLocation) {
        this.dLocation = dLocation;
    }

    public String getdImageId() {
        return dImageId;
    }

    public void setdImageId(String dImageId) {
        this.dImageId = dImageId;
    }
}
