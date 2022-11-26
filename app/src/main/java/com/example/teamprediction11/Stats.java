package com.example.teamprediction11;

public class Stats {
    String name;
    Float str, avg;
    Integer bf, num4s, num6s;
    Stats(String name, Float str, Float avg, Integer bf, Integer num4s, Integer num6s){
        this.name = name;
        this.str = str;
        this.avg = avg;
        this.bf = bf;
        this.num4s = num4s;
        this.num6s = num6s;
    }

    String getName() {
        return name;
    }

    Float getAvg() {
        return avg;
    }

    Float getStr() {
        return str;
    }

    Integer getBf() {
        return bf;
    }

    Integer getNum4s() {
        return num4s;
    }

    Integer getNum6s() {
        return num6s;
    }
}
