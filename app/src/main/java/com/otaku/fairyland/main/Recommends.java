package com.otaku.fairyland.main;

import java.io.Serializable;

/**
 * Created by ${wangjishan} on 2016/11/25.
 *
 * @version V1.0
 * @Description:
 */

public class Recommends implements Serializable {



    private String defaultpic;
    private String videoaddress;
    private String imagecollection;
    private String description;
    private String category;

    public Recommends() {

    }

    public String getDefaultpic() {
        return defaultpic;
    }

    public void setDefaultpic(String defaultpic) {
        this.defaultpic = defaultpic;
    }

    public String getVideoaddress() {
        return videoaddress;
    }

    public void setVideoaddress(String videoaddress) {
        this.videoaddress = videoaddress;
    }

    public String getImagecollection() {
        return imagecollection;
    }

    public void setImagecollection(String imagecollection) {
        this.imagecollection = imagecollection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Recommends{" +
                "defaultpic='" + defaultpic + '\'' +
                ", videoaddress='" + videoaddress + '\'' +
                ", imagecollection='" + imagecollection + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
