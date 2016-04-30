package com.vit.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by kashishsinghal on 25/03/16.
 */
@Entity
@Table(name = "shuttle_location")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShuttleLocation {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "latitude")
    double latitude;

    @Column(name = "longitude")
    double longitude;

    @Column(name = "car_id")
    int carId;

    @Column(name = "car_time")
    Date carTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Date getCarTime() {
        return carTime;
    }

    public void setCarTime(Date carTime) {
        this.carTime = carTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShuttleLocation)) return false;

        ShuttleLocation that = (ShuttleLocation) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ShuttleLocation{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", carId=" + carId +
                ", carTime=" + carTime +
                '}';
    }
}
