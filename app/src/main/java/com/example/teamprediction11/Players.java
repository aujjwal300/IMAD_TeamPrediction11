package com.example.teamprediction11;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class Players implements Serializable {
    String name, desc;
    Integer img;

    Players(String name, Integer img, String desc){
        this.name = name;
        this.img = img;
        this.desc = desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getName(){
        return name;
    }
    Integer getImage(){
        return img;
    }
    String getDesc(){
        return desc;
    }
}
