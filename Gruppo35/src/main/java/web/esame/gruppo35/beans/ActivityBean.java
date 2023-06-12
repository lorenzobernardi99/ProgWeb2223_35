package web.esame.gruppo35.beans;

import java.io.Serializable;

public class ActivityBean implements Serializable {

    private static int serial=0;
    private int id;
    private String name;
    private String description;
    private String imagePath;
    private boolean subscribed;

    public ActivityBean(){}

    public ActivityBean(String name, String description, String imagePath) {
        this.id = ++ActivityBean.serial;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
    public boolean isSubscribed() {
        return subscribed;
    }
    public int getId() {
        return id;
    }
    public String getName() { return name; }
    public String getDescription() {
        return description;
    }
    public String getImagePath() {
        return imagePath;
    }
}
