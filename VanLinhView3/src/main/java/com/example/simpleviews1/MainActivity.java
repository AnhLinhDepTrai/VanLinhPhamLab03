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
        Log.d(getString(R.string.log1), getString(R.string.logstuff));
    }

    private int[] images = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4
    };

    private String[] imageNames;


    private int currentImageIndex = 0;


    public void btnSaved_clicked (View view) {
        DisplayToast(getString(R.string.savbtn));
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageNames = new String[] {
                getString(R.string.predator),
                getString(R.string.kirk),
                getString(R.string.ow),
                getString(R.string.tzu)
        };
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.mrun)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.messagealeart)
                        .setCancelable(false)
                        .setPositiveButton(R.string.Y, (dialog, which) -> finish())
                        .setNegativeButton(R.string.N, (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
        Switch switch1 = findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String message;
                if (isChecked) {
                    message = getString(R.string.swoff);
                } else {
                    message = getString(R.string.swofff);
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

                Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                startActivity(intent);


            }
        });



        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // Intent to open a website
                String url = getString(R.string.thelink);
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