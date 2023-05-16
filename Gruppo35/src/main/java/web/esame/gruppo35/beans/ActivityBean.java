package web.esame.gruppo35.beans;

import java.io.Serializable;

public class ActivityBean implements Serializable {
    private static int serial=0;
    private final String id;
    private String name;
    private String description;
    private String imagePath;

    public ActivityBean(){this.id = String.valueOf((++ActivityBean.serial));};

    public ActivityBean(int id, String name, String description, String imagePath) {
        this.id = String.valueOf((++ActivityBean.serial));
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getImagePath() {
        return imagePath;
    }
}
