package com.example.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reminderapplication.databinding.ActivityHomeBinding;

import org.w3c.dom.Text;

//main home screen user interacts with after putting in info
public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private ActivityHomeBinding binding;
    private String name;
    private String email;
    private String phone;
    private boolean fabMenuOpen;
    private boolean reminderMenuOpen;
    private boolean taskMenuOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: HomeActivity Started");

        //used to access .xml objects
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //changing the setting icon
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_settings_24);
        binding.toolbar.setOverflowIcon(drawable);
        //initializing buttons
        binding.button1.setVisibility(View.GONE);
        binding.button2.setVisibility(View.GONE);
        Log.d(TAG, "onCreate: fab menu items hidden");

        //loading variables via intent
        Intent intent = getIntent();
        name = intent.getStringExtra(MainActivity.NAME_KEY);
        email = intent.getStringExtra(MainActivity.EMAIL_KEY);
        phone = intent.getStringExtra(MainActivity.PHONE_KEY);
        binding.toolbar.setTitle("Hello " + name + "!");

        Log.d(TAG, "onCreate: user data imported from intent");

        setSupportActionBar(binding.toolbar);

        //fab menu
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fabMenuOpen)
                {
                    showMenu();
                }
                else
                {
                    closeMenu();
                }
            }
        });

        //reminder window open
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!reminderMenuOpen)
                {
                    createNewReminder();
                }
            }
        });

        //reminder window close
        binding.reminderWindow.reminderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeReminder();
            }
        });

        Log.d(TAG, "onClick: name = " + name);
        Log.d(TAG, "onClick: email = " + email);
        Log.d(TAG, "onClick: phone = " + phone);



    }

    private void showMenu() {
        fabMenuOpen = true;
        binding.button1.setVisibility(View.VISIBLE);
        binding.button2.setVisibility(View.VISIBLE);
        //distances that the buttons will move during animation
        binding.button1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        binding.button2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        Log.d(TAG, "onCreate: fab menu opened");
    }

    private void closeMenu() {
        fabMenuOpen = false;
        binding.button1.animate().translationY(0);
        binding.button2.animate().translationY(0);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                binding.button1.setVisibility(View.GONE);
                binding.button2.setVisibility(View.GONE);
            }
        }, 320);
        Log.d(TAG, "onCreate: fab menu closed");
    }

    private void createNewReminder() {
        if(fabMenuOpen)
        {
            closeMenu();
        }
        reminderMenuOpen = true;
        binding.reminderWindow.getRoot().setVisibility(View.VISIBLE);

    }

    private void closeReminder() {
        reminderMenuOpen = false;
        binding.reminderWindow.getRoot().setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void toastMessage(String message) {
        Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}