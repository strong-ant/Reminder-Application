package com.example.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

//main home screen user interacts with after putting in info
public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private TextView nameText;
    //private TextView emailText;
    //private TextView phoneText;
    private String name;
    private String email;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: HomeActivity Started");

        nameText = (TextView) findViewById(R.id.nameText);
        //emailText = (TextView) findViewById(R.id.emailText);
        //phoneText = (TextView) findViewById(R.id.phoneText);

        Intent intent = getIntent();
        name = intent.getStringExtra(MainActivity.NAME_KEY);
        email = intent.getStringExtra(MainActivity.EMAIL_KEY);
        phone = intent.getStringExtra(MainActivity.PHONE_KEY);
        nameText.setText("Hello " + name + "!");
        //emailText.setText(email);
        //phoneText.setText(phone);
        Log.d(TAG, "onClick: name = " + name);
        Log.d(TAG, "onClick: email = " + email);
        Log.d(TAG, "onClick: phone = " + phone);

    }
    public void toastMessage(String message)
    {
        Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}