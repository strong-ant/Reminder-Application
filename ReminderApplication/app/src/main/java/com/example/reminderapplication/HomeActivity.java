package com.example.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reminderapplication.databinding.ActivityHomeBinding;
import com.example.reminderapplication.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

//main home screen user interacts with after putting in info
public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private ActivityHomeBinding binding;
    private String name;
    private String email;
    private String phone;
    private boolean fabMenuOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: HomeActivity Started");

        //used to access .xml objects
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button1.setVisibility(View.INVISIBLE);
        binding.button2.setVisibility(View.INVISIBLE);
        binding.button1.setClickable(false);
        binding.button2.setClickable(false);

        Intent intent = getIntent();
        name = intent.getStringExtra(MainActivity.NAME_KEY);
        email = intent.getStringExtra(MainActivity.EMAIL_KEY);
        phone = intent.getStringExtra(MainActivity.PHONE_KEY);
        binding.toolbar.setTitle("Hello " + name + "!");

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

        Log.d(TAG, "onClick: name = " + name);
        Log.d(TAG, "onClick: email = " + email);
        Log.d(TAG, "onClick: phone = " + phone);

    }

    private void showMenu()
    {
        fabMenuOpen = true;
        binding.button1.setVisibility(View.VISIBLE);
        binding.button2.setVisibility(View.VISIBLE);
        binding.button1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        binding.button2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        binding.button1.setClickable(true);
        binding.button2.setClickable(true);
    }

    private void closeMenu()
    {
        fabMenuOpen = false;
        binding.button1.animate().translationY(0);
        binding.button2.animate().translationY(0);
        binding.button1.setClickable(false);
        binding.button2.setClickable(false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                binding.button1.setVisibility(View.INVISIBLE);
                binding.button2.setVisibility(View.INVISIBLE);
            }
        }, 320);

    }

    public void toastMessage(String message)
    {
        Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}