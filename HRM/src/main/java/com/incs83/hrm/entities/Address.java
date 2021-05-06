package com.incs83.hrm.entities;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address extends Parent  {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "pin_code")
    private String pinCode;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "house_number")
    private int houseNumber;
    @Column(name = "colony")
    private String colony;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", pinCode='" + pinCode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", houseNumber=" + houseNumber +
                ", colony='" + colony + '\'' +
                '}';
    }
}
