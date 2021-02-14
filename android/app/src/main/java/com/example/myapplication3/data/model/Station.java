package com.example.myapplication3.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(tableName = "Station")
@IgnoreExtraProperties
public class Station {

    @PrimaryKey
    @NonNull
    // 좌측 상단 좌표
    public Double X1;
    public Double Y1;
    
    // 우측 하단 좌표
    public Double X2;
    public Double Y2;
    
    // 역 이름
    public String stationName;

    public Station() {
        // Default constructor required for calls to DataSnapshot.getValue(Cert.class)
    }

    public Station(Double X1, Double Y1, Double X2, Double Y2, String stationName) {
        this.X1 = X1;
        this.Y1 = Y1;
        this.X2 = X2;
        this.Y2 = Y2;
        this.stationName = stationName;
    }

    public Double getX1() {return X1;}

    public void setX1(Double X1) {this.X1 = X1;}

    public Double getY1() {return Y1;}

    public void setY1(Double Y1) {this.X1 = Y1;}

    public Double getX2() {return X2;}

    public void setX2(Double X2) {this.X2 = X2;}

    public Double getY2() {return Y2;}

    public void setY2(Double Y2) {this.X2 = Y2;}

    @Override
    public String toString() {
        return "Station{" +
                "X1" + X1 + '\'' +
                ", Y1" + Y1 + '\'' +
                ", X2" + X2 + '\'' +
                ", Y2" + Y2 + '\'' +
                '}';
    }
}
