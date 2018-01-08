package com.example.indraputramr.koinworkstest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indraputramr.koinworkstest.ConstructorCollection.UserConstructor;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by indraputramr on 07/01/2018.
 */

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1;

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmPasswordEditText;
    private TextView mLoginLink;
    private Button mSignupButton;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUsersDatabaseReference;
    private FirebaseDatabase mLocationFirebaseDatabase;
    private FirebaseDatabase mSmsFirebaseDatabase;
    private FirebaseDatabase mContactFirebaseDatabase;
    private FirebaseDatabase mCallLogFirebaseDatabase;

    private DatabaseReference mLocationDatabaseReference;
    private DatabaseReference mSmsDatabaseReference;
    private DatabaseReference mContactDatabaseReference;
    private DatabaseReference mCallLogDatabaseReference;

    public static String mKeyPair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");

        mEmailEditText = (EditText) findViewById(R.id.email_address_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
        mConfirmPasswordEditText = (EditText) findViewById(R.id.repeat_password_edit_text);

        mSignupButton = (Button) findViewById(R.id.button_signup);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
                Log.d("Post Key, abis signup" , mKeyPair);
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mLoginLink = (TextView) findViewById(R.id.text_view_login);

        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        mSignupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String reEnterPassword = mConfirmPasswordEditText.getText().toString();

        UserConstructor user = new UserConstructor(email, password);
        //push creates a unique id in database
        mKeyPair = mUsersDatabaseReference.push().getKey();

        Log.d("Post Key" , mKeyPair);
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users").child(mKeyPair);
        mUsersDatabaseReference.setValue(user);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }

    public String getKeyPair() {
        return mKeyPair;
    }

    public void onSignupSuccess() {
        mSignupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();
        mSignupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String reEnterPassword = mConfirmPasswordEditText.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEditText.setError("enter a valid email address");
            valid = false;
        } else {
            mEmailEditText.setError(null);
        }

        if (password.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            mConfirmPasswordEditText.setError("Password do not match");
            valid = false;
        } else {
            mConfirmPasswordEditText.setError(null);
        }

        return valid;
    }

}
