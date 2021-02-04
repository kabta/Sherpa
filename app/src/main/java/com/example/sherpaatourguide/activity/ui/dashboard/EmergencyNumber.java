package com.example.sherpaatourguide.activity.ui.dashboard;

public class EmergencyNumber {

    private String primaryPhone,  title;


    public EmergencyNumber(String primaryPhone, String title) {
        this.primaryPhone = primaryPhone;

        this.title = title;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String phone) {
        this.primaryPhone = phone;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
