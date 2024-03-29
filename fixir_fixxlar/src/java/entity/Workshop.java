/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Fixxlar
 */
public class Workshop {

    private int id;
    private String name;
    private String email;
    private String description;
    private String website;
    private String address;
    private String openingHour;
    private String openingHourFormat;
    private double latitude;
    private double longitude;
    private String contact;
    private String contact2;
    private String location;
    private String specialize;
    private String category;
    private String carBrands;
    private String remark;
    private byte isActive;

    public Workshop(int id, String email, String name, String description, String website, String address, String openingHour,
            String openingHourFormat, double latitude, double longitude, String contact, String contact2, String location,
            String specialize, String category, String carBrands, String remark, byte isActive) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.id = id;
        this.description = description;
        this.carBrands = carBrands;
        this.website = website;
        this.openingHour = openingHour;
        this.openingHourFormat = openingHourFormat;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contact = contact;
        this.contact2 = contact2;
        this.location = location;
        this.specialize = specialize;
        this.category = category;
        this.remark = remark;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }

    public String getOpeningHour() {
        return openingHour;
    }

    public String getOpeningHourFormat() {
        return openingHourFormat;
    }

    public String getContact() {
        return contact;
    }

    public String getContact2() {
        return contact2;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocation() {
        return location;
    }

    public String getSpecialize() {
        return specialize;
    }

    public String getCarBrands() {
        return carBrands;
    }

    public String getRemark() {
        return remark;
    }

    public String getCategory() {
        return category;
    }

    public byte getIsActive() {
        return isActive;
    }
}
