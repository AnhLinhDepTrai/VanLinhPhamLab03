package com.example.simpleviews1;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activity101", "Linh Pham Van - Student ID: N01681546");
    }

    private int[] images = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4
    };

    private String[] imageNames = {
            "Mr.Jeffrey", "We are Charlie Kirk", "Meowl", "sunTzu"
    };

    private int currentImageIndex = 0;


    public void btnSaved_clicked (View view) {
        DisplayToast(getString(R.string.savbtn));
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // show your AlertDialog here
                new AlertDialog.Builder(MainActivity.this)
                       .setIcon(R.drawable.mrun) // custom icon
                        .setTitle(R.string.app_name)       // your project name
                        .setMessage(R.string.messagealeart)
                        .setCancelable(false)           // must choose Yes or No
                        .setPositiveButton("Yes", (dialog, which) -> finish())
                        .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
        Switch switch1 = findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String message;
                if (isChecked) {
                    message = "Switch is ON";
                } else {
                    message = "Switch is OFF";
                }

                // Display Snackbar
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                        .show();
            }
        });

        ImageButton imgButton = findViewById(R.id.btnImg1);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                currentImageIndex = (currentImageIndex + 1) % images.length;
                imgButton.setImageResource(images[currentImageIndex]);





                Toast.makeText(MainActivity.this,
                        getString(R.string.tstname) + imageNames[currentImageIndex],
                        Toast.LENGTH_SHORT).show();
            }
        });


        //---Button view---
        Button btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch device settings
                Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                startActivity(intent);

                // Remove the toast, so nothing else happens
            }
        });



		Button btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener()
		{
            @Override
            public void onClick(View v) {
                // Intent to open a website
                String url = "https://www.youtube.com/watch?v=ReXHwSS0r5U"; // put your website here
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
		});


        //---CheckBox---
        CheckBox checkBox = (CheckBox) findViewById(R.id.chkAutosave);
        checkBox.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if (((CheckBox)v).isChecked())
                    DisplayToast(getString(R.string.cheked));
                else
                    DisplayToast(getString(R.string.ucheked));
            }
        });

        //---RadioButton---
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdbGp1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) findViewById(R.id.rdb1);
                if (rb1.isChecked()) {
                    DisplayToast(getString(R.string.op1));
                } else {
                    DisplayToast(getString(R.string.op2));
                }
            }
        });

        //---ToggleButton---
        ToggleButton toggleButton =
                (ToggleButton) findViewById(R.id.toggle1);
        toggleButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if (((ToggleButton)v).isChecked())
                    DisplayToast(getString(R.string.ontg));
                else
                    DisplayToast(getString(R.string.offtg));
            }
        });
    }

    private void DisplayToast(String msg)
    {
        Toast.makeText(getBaseContext(), msg,
                Toast.LENGTH_SHORT).show();
    }
}