package com.example.ead_2022_a1;

public class Item {
    private String title;
    private String description;
    private String time;
    boolean isComplete;
    String date;
    public String getTaskTitle() {
        return title;
    }
    public void setTaskTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLastAlarm() {
        return time;
    }
    public void setLastAlarm(String location) {
        this.time = time;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
    public boolean isComplete() {
        return isComplete;
    }

    //constructor
    public Item(String title) {
        this.title = title;
    }
}
