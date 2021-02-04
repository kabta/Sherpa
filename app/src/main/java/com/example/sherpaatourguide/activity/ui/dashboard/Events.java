package com.example.sherpaatourguide.activity.ui.dashboard;

public class Events<string> {


    private String eventsname;
    private String eventsdate;
    private int eventimg;

    public Events(String eventsdate, string eventsname, int eventimg) {
        this.eventsname = (String) eventsname;
        this.eventsdate = eventsdate;
        this.eventimg = eventimg;
    }


    public String getEventsname() {
        return eventsname;
    }

    public void setEventsname(String eventsname) {
        this.eventsname = (String) eventsname;
    }

    public String getEventsdate() {
        return eventsdate;
    }

    public void setEventsdate(String eventsdate) {
        this.eventsdate = eventsdate;
    }

    public int getEventimg() {
        return eventimg;
    }

    public void setEventimg(int eventimg) {
        this.eventimg = eventimg;
    }

}


