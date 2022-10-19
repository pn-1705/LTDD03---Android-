package com.example.androidapp1.model;

import java.io.Serializable;
import java.util.List;

public class Store implements Serializable {
    private String phone;
    private String name;
    private int resourceId;
    private String address;

    public Store(String phone, String name, int resourceId, String address) {
        this.phone = phone;
        this.name = name;
        this.resourceId = resourceId;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
