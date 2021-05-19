package com.bootcamp.hrm.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "address")
public class Address extends Parent {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "address_id", columnDefinition = "VARCHAR(255)")
    private UUID id;
    @Column(name = "pin_code")
    private String pinCode;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "colony")
    private String colony;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
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
