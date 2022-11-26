package com.example.teamprediction11;

public class Players {
    String name, desc;
    Integer mImage;
    Players(String name, Integer img, String desc){
        this.name = name;
        this.mImage = img;
        this.desc = desc;
    }

    String getName(){
        return name;
    }
    Integer getImage(){
        return mImage;
    }
    String getDesc(){
        return desc;
    }
}
