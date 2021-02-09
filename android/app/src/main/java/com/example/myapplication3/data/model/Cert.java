package com.example.myapplication3.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(tableName = "Cert")
@IgnoreExtraProperties
public class Cert {

    @PrimaryKey
    @NonNull
    public Long certNumber;
    public String certName;
    public Boolean isCert;

    public Cert() {
        // Default constructor required for calls to DataSnapshot.getValue(Cert.class)
    }

    public Cert(Long certNumber, String certName, Boolean isCert) {
        this.certNumber = certNumber;
        this.certName = certName;
        this.isCert = isCert;
    }

    public Long getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(Long certNumber) {
        this.certNumber = certNumber;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String password) {
        this.certName = certName;
    }

    public Boolean getIsCert() { return isCert; }

    public void setIsCert(Boolean isCert) { this.isCert = isCert; }

    @Override
    public String toString() {
        return "Cert{" +
                "certNumber='" + certNumber + '\'' +
                ", certName='" + certName + '\'' +
                ", isCert='" + isCert + '\'' +
                '}';
    }
}
