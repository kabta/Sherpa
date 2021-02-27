package com.example.sherpaatourguide.activity.ui;

public class ShoppingMallsData {
    private String Shopname;
    private String ShopDescription;
    private String ShopLocation;
    private String ShopPhone;
    private String ImageId;
    public ShoppingMallsData() {

    }

    public ShoppingMallsData(String shopname, String shopDescription, String shopLocation, String shopPhone, String imageId) {
        Shopname = shopname;
        ShopDescription = shopDescription;
        ShopLocation = shopLocation;
        ShopPhone = shopPhone;
        ImageId = imageId;
    }

    public String getShopname() {
        return Shopname;
    }

    public void setShopname(String shopname) {
        Shopname = shopname;
    }

    public String getShopDescription() {
        return ShopDescription;
    }

    public void setShopDescription(String shopDescription) {
        ShopDescription = shopDescription;
    }

    public String getShopLocation() {
        return ShopLocation;
    }

    public void setShopLocation(String shopLocation) {
        ShopLocation = shopLocation;
    }

    public String getShopPhone() {
        return ShopPhone;
    }

    public void setShopPhone(String shopPhone) {
        ShopPhone = shopPhone;
    }

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }
}
