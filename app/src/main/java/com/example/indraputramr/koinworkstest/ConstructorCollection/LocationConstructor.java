package com.example.indraputramr.koinworkstest.ConstructorCollection;

/**
 * Created by indraputramr on 07/01/2018.
 */

public class LocationConstructor {

    private String mLatitude;
    private String mLongitude;
    private String mUpdateTime;

    public LocationConstructor() {}

    public LocationConstructor(String latitude, String longitude, String updateTime) {
        mLatitude = latitude;
        mLongitude = longitude;
        mUpdateTime = updateTime;
    }

    public String getLatitude() { return mLatitude; }

    public void setLatitude(String latitude) { this.mLatitude = latitude; }

    public String getLongitude() { return mLongitude; }

    public void setLongitude(String longitude) { this.mLongitude = longitude; }

    public String getUpdateTime() { return mUpdateTime; }

    public void setUpdateTime(String updateTime) { this.mUpdateTime = updateTime; }



}
