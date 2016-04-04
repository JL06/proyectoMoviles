package com.example.jl.projectmobile;

/**
 * Created by JL on 04/04/16.
 */
public class Event {

    private int id;
    private String title;
    private int image;
    private String description;

    public Event(int id, String title_arg, int img_arg, String description_arg) {
        this.id = id;
        this.title = title_arg;
        this.image = img_arg;
        this.description = description_arg;
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


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String des) {
        this.description = des;
    }
}
