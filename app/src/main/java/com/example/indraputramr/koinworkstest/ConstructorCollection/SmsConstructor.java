package com.example.indraputramr.koinworkstest.ConstructorCollection;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by indraputramr on 07/01/2018.
 */

public class SmsConstructor {

    private String mSender;
    private String mDate;
    private String mBody;

    public SmsConstructor() {}

    public SmsConstructor(String sender, String date, String body) {
        mSender = sender;
        mDate = date;
        mBody = body;
    }

    public String getSender() { return mSender; }

    public void setSender(String sender) { this.mSender = sender; }

    public String getDate() { return mDate; }

    public void setDate(String date) { this.mDate = date; }

    public String getBody() { return mBody; }

    public void setBody(String body) { this.mBody = body; }

}
