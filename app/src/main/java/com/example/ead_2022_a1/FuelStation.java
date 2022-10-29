package com.example.ead_2022_a1;

public class FuelStation {

    String userName , name ,location, petrolAmount , dieselAmount;

    public FuelStation(String userName, String name, String location , String petrolAmount, String dieselAmount) {
        this.userName = userName;
        this.name = name;
        this.location = location;
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
}
