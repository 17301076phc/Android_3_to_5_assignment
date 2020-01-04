package com.example.firstassignment;

import java.io.Serializable;

public class Course implements Serializable {
    private String id;
    private String name;
    private String code;
    private String categoryId;
    private String description;
    private String price;
    private String status;
    private String openDate;
    private String lastUpdateOn;
    private String level;
    private String shared;
    private String sharedUrl;
    private String avatar;
    private String bigAvatar;
    private String certification;
    private String certificationDuration;

    public String getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getCode() {
        return code;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public String getDescription() {
        return description;
    }
    public String getPrice() {
        return price;
    }
    public String getStatus() {
        return status;
    }
    public String getOpenDate() {
        return openDate;
    }
    public String getLastUpdateOn() {
        return lastUpdateOn;
    }
    public String getLevel() {
        return level;
    }
    public String getShared() {
        return shared;
    }
    public String getSharedUrl() {
        return sharedUrl;
    }
    public String getAvatar() {
        return avatar;
    }
    public String getBigAvatar() {
        return bigAvatar;
    }
    public String getCertification() {
        return certification;
    }
    public String getCertificationDuration() {
        return certificationDuration;
    }

//SET
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setBigAvatar(String bigAvatar) {
        this.bigAvatar = bigAvatar;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public void setCertificationDuration(String certificationDuration) {
        this.certificationDuration = certificationDuration;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastUpdateOn(String lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public void setSharedUrl(String sharedUrl) {
        this.sharedUrl = sharedUrl;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
