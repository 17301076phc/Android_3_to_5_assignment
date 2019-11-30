package com.example.firstassignment;

import java.io.Serializable;

public class Course implements Serializable {
    private String name;
    private int imageid;

    public Course(String name,int imageid){
        this.name=name;
        this.imageid=imageid;
    }

    public String getName(){
        return name;
    }

    public int getImageid() {
        return imageid;
    }
}
