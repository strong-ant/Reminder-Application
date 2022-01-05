package com.example.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reminderapplication.databinding.ActivityMainBinding;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String name;
    private String email;
    private String phone;


    private static final String TAG = "MainActivity"; //tag for logs
    //keys used for variables through intent and preferences
    public static final String NAME_KEY = "com.example.reminderapplication.NAME_KEY";
    public static final String EMAIL_KEY = "com.example.reminderapplication.EMAIL_KEY";
    public static final String PHONE_KEY = "com.example.reminderapplication.PHONE_KEY";
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");

        //used to access .xml objects
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(preferencesExists()) //check if data has been saved. if so, open home activity
        {
            openHomeActivity();
            finish();
        }
        else //no save data
        {
            binding.nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name = binding.editTextTextPersonName.getEditableText().toString();
                    email = binding.editTextTextEmailAddress.getEditableText().toString();
                    phone = binding.editTextPhone.getEditableText().toString();

                    if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone))
                    {
                        toastMessage("Please fill out all fields");
                    }
                    else if(!PhoneNumberUtils.isGlobalPhoneNumber(phone))
                    {
                        toastMessage("Please enter a valid phone number");
                    }
                    else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    {
                        toastMessage("Please enter a valid email");
                    }
                    else
                    {
                        Log.d(TAG, "onClick: Clicked Next Button");
                        Log.d(TAG, "onClick: name = " + name);
                        Log.d(TAG, "onClick: email = " + email);
                        Log.d(TAG, "onClick: phone = " + phone);
                        saveData();
                        openHomeActivity();
                        finish();
                    }
                }
            });
        }


    }

    public void openHomeActivity() //opens the main home page and passes the main user variables
    {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(NAME_KEY, name);
        intent.putExtra(EMAIL_KEY, email);
        intent.putExtra(PHONE_KEY, phone);
        startActivity(intent);
    }

    //saves user input on close
    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME_KEY, name);
        editor.putString(EMAIL_KEY, email);
        editor.putString(PHONE_KEY, phone);
        editor.apply();
        toastMessage("Data Saved");
        Log.d(TAG, "saveData: Saved Data to Preferences");
    }

    public void loadData() //loads data from preferences
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        name = sharedPreferences.getString(NAME_KEY, "");
        email = sharedPreferences.getString(EMAIL_KEY, "");
        phone = sharedPreferences.getString(PHONE_KEY, "");
        Log.d(TAG, "loadData: Data Loaded From Preferences");
    }

    //checks if shared preferences exist by loading the data and checking if the variables are empty
    private boolean preferencesExists()
    {
        Log.d(TAG, "preferencesExists: checking");
        loadData();
        if(name.isEmpty() || phone.isEmpty() || email.isEmpty())
        {
            Log.d(TAG, "preferencesExists: no preferences detected");
            return false;
        }
        else
        {
            Log.d(TAG, "preferencesExists: preferences found");
            return true;
        }
    }

    //utility method for displaying toast messages
    public void toastMessage(String message)
    {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}