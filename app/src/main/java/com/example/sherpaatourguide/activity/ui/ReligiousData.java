package com.example.sherpaatourguide.activity.ui;

public class ReligiousData {
    private String reName;
    private String reDescription;
    private String reLocation;

    private String reImageId;

    public ReligiousData() {

    }
    public ReligiousData(String reName, String reDescription, String reLocation , String reImageId) {
        this.reName = reName;
        this.reDescription = reDescription;
        this.reLocation = reLocation;

        this.reImageId = reImageId;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName;
    }

    public String getReDescription() {
        return reDescription;
    }

    public void setReDescription(String reDescription) {
        this.reDescription = reDescription;
    }

    public String getReLocation() {
        return reLocation;
    }

    public void setReLocation(String reLocation) {
        this.reLocation = reLocation;
    }


    public String getReImageId() {
        return reImageId;
    }

    public void setReImageId(String reImageId) {
        this.reImageId = reImageId;
    }
}
