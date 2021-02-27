package com.example.sherpaatourguide.activity.ui;

public class RestaurantData {
    private String Name;
    private String Description;
    private String Location;
    private String Phone;
    private String ImageId;

    public RestaurantData() {

    }
    public RestaurantData(String name, String description, String location, String phone, String imageId) {
        Name = name;
        Description = description;
        Location = location;
        Phone = phone;
        ImageId = imageId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }
}
