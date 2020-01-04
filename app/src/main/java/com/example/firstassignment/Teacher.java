package com.example.firstassignment;

public class Teacher {
    private String userid;
    private String courseId;
    private String name;
    private String photo;
    private String telephone;
    private String email;
    private String description;

    public String getUserid() {
        return userid;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }


    public String getDescription() {
        return description;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
