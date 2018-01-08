package com.example.indraputramr.koinworkstest.ConstructorCollection;

/**
 * Created by indraputramr on 07/01/2018.
 */

public class CallLogConstructor {

    private String mPhoneNumber;
    private String mCallType;
    private String mDateTime;
    private String mDuration;

    public CallLogConstructor() {}

    public CallLogConstructor(String phoneNumber, String callType, String dateTime, String duration) {
        mPhoneNumber = phoneNumber;
        mCallType = callType;
        mDateTime = dateTime;
        mDuration = duration;
    }

    public String getPhoneNumber() { return mPhoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.mPhoneNumber = phoneNumber; }

    public String getCallType() { return mCallType; }

    public void setCallType(String callType) { this.mCallType = callType; }

    public String getDateTime() { return mDateTime; }

    public void setDateTime(String dateTime) { this.mDateTime = dateTime; }

    public String getDuration() { return mDuration; }

    public void setDuration(String duration) { this.mDuration = duration; }

}
