package com.example.firstassignment;

public class Course {
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
