package com.example.myapplication3.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(tableName = "Seat")
@IgnoreExtraProperties
public class Seat {
    @PrimaryKey
    @NonNull
    public Boolean isSit;
    public Long isSitValue;
    public Long sensorValue;
    public String seatUser;

    public Seat() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Seat(Boolean isSit, Long isSitValue, Long sensorValue, String seatUser) {
        this.isSit = isSit;
        this.isSitValue = isSitValue;
        this.sensorValue = sensorValue;
        this.seatUser = seatUser;
    }

    public Boolean getIsSit() { return isSit; }

    public void setIsSit(Boolean isSit) { this.isSit = isSit; }

    public Long getIsSitValue() { return isSitValue; }

    public void setIsSitValue(Long isSitValue) { this.isSitValue = isSitValue; }

    public Long getSensorValue() { return sensorValue; }

    public void setSensorValue(Long sensorValue) { this.sensorValue = sensorValue; }

    public String getSeatUser() { return seatUser; }

    public void setSeatUser(String seatUser) { this.seatUser = seatUser; }

    @Override
    public String toString() {
        return "Seat{" +
                "isSit='" + isSit + '\'' +
                ", isSitValue='" + isSitValue + '\'' +
                ", sensorValue='" + sensorValue + '\'' +
                '}';
    }
}
