package com.vit.model;

import java.io.Serializable;

/**
 * Created by User on 11-Apr-16.
 */
public class CarData extends BaseObject implements Serializable {

    int id;
    String name;

    String brand;

    String thumbnail;

    CarDetails details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CarDetails getDetails() {
        return details;
    }

    public void setDetails(CarDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "CarData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", details=" + details +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarData carData = (CarData) o;

        return id == carData.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}