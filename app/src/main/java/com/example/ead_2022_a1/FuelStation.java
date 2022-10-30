package com.example.ead_2022_a1;

public class FuelStation {

    String userName , name ,location, petrolAmount , dieselAmount , arrivalTime , arrivalDate , finishTime , finishDate;

    public FuelStation() {

    }

    public FuelStation(String userName, String name, String location , String petrolAmount, String dieselAmount) {
        this.userName = userName;
        this.name = name;
        this.location = location;
        this.petrolAmount = petrolAmount;
        this.dieselAmount = dieselAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPetrolAmount() {
        return petrolAmount;
    }

    public void setPetrolAmount(String petrolAmount) {
        this.petrolAmount = petrolAmount;
    }

    public String getDieselAmount() {
        return dieselAmount;
    }

    public void setDieselAmount(String dieselAmount) {
        this.dieselAmount = dieselAmount;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
