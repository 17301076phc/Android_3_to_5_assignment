package com.example.firstassignment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
@Entity
public class CourseIndro implements Serializable {
    @PrimaryKey
    private int id;
    private String name;
    private int imageid;

    public CourseIndro(int id,String name,int imageid){
        this.id=id;
        this.name=name;
        this.imageid=imageid;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public int getImageid() {
           return imageid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageid(int imageid) {
     this.imageid = imageid;
     }

    public void setName(String name) {
        this.name = name;
    }
}
