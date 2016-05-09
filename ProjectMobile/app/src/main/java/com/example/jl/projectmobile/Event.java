package com.example.jl.projectmobile;

/**
 * Created by JL on 04/04/16.
 */
public class Event {

    private int id;
    private String title;
    private String description;
    private String date;
    private String place;

    public Event(int id, String title_arg, String description_arg, String date_arg, String place_arg) {
        this.id = id;
        this.title = title_arg;
        this.description = description_arg;
        this.date = date_arg;
        this.place = place_arg;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String des) {
        this.description = des;
    }


    public String getDate() {
        return  date;
    }

    public  void setDate (String dat) {
        this.date = dat;
    }


    public String getPlace() {
        return place;
    }

    public  void setPlace (String pl) {
        this.place = pl;
    }

}
