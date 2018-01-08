package com.example.indraputramr.koinworkstest.ConstructorCollection;

import android.provider.ContactsContract;

/**
 * Created by indraputramr on 07/01/2018.
 */

public class ContactConstructor {

    private String mContactName;
    private String mPhoneNumber;

    public ContactConstructor() {}

    public ContactConstructor(String contactName, String phoneNumber) {
        mContactName = contactName;
        mPhoneNumber = phoneNumber;
    }

    public String getContactName() { return mContactName; }

    public void setContactName(String contactName) { this.mContactName = contactName; }

    public String getPhoneNumber() { return mPhoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.mPhoneNumber = phoneNumber; }

}
