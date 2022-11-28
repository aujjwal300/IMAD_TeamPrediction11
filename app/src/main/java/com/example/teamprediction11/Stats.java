package com.example.teamprediction11;

import androidx.annotation.Keep;

@Keep
public class Stats {
    public String player_name;
    public Float str, avg, bf, num4s, num6s;
    Stats(){

    }
    Stats(String player_name, Float str, Float avg, Float bf, Float num4s, Float num6s){
        this.player_name = player_name;
        this.str = str;
        this.avg = avg;
        this.bf = bf;
        this.num4s = num4s;
        this.num6s = num6s;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setAvg(Float avg) {
        this.avg = avg;
    }

    public void setBf(Float bf) {
        this.bf = bf;
    }

    public void setNum4s(Float num4s) {
        this.num4s = num4s;
    }

    public void setNum6s(Float num6s) {
        this.num6s = num6s;
    }

    public void setStr(Float str) {
        this.str = str;
    }

    public String getPlayer_name() {
        return player_name;
    }

    Float getAvg() {
        return avg;
    }

    Float getStr() {
        return str;
    }

    Float getBf() { return bf; }

    Float getNum4s() {
        return num4s;
    }

    Float getNum6s() {
        return num6s;
    }
}
