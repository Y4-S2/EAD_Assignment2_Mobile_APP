package com.example.ead_2022_a1;

public class FuelStation {

    String userName , name ,location, petrolAmount , dieselAmount , petrolArrivalTime , dieselArrivalTime , petrolArrivalDate , dieselArrivalDate;

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

    public String getPatrolArrivalTime() {
        return petrolArrivalTime;
    }

    public void setPatrolArrivalTime(String patrolArrivalTime) {
        this.petrolArrivalTime = patrolArrivalTime;
    }

    public String getDieselArrivalTime() {
        return dieselArrivalTime;
    }

    public void setDieselArrivalTime(String dieselArrivalTime) {
        this.dieselArrivalTime = dieselArrivalTime;
    }

    public String getPatrolArrivalDate() {
        return petrolArrivalDate;
    }

    public void setPatrolArrivalDate(String patrolArrivalDate) {
        this.petrolArrivalDate = patrolArrivalDate;
    }

    public String getDieselArrivalDate() {
        return dieselArrivalDate;
    }

    public void setDieselArrivalDate(String dieselArrivalDate) {
        this.dieselArrivalDate = dieselArrivalDate;
    }
}
