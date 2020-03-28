package com.example.triviaapp.model;


import java.io.Serializable;

public class UserInfo implements Serializable {

    private String id;
    private String name;
    private String answer1;
    private String answer2;
    private String dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserInfo() {}
    public UserInfo(String id, String name, String answer1, String answer2, String dateTime) {
        this.id = id;
        this.name = name;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
}
