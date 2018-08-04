package com.agzuniverse.agz.reachout;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class Main extends Activity {
    private CustomButton getHelpButton;
    private CustomButton giveHelpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                Log.d("loc", String.valueOf(location));
                            } else Log.d("loc", "NOT FOUND");
                        }
                    });
        }

        setContentView(R.layout.activity_main);
//        Button btn = findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v){
//                startActivity(new Intent(Main.this, GetHelp.class));
//            }
//        });
        getHelpButton = findViewById(R.id.getHelpBtn);
        giveHelpButton = findViewById(R.id.giveHelpBtn);



        getHelpButton.setButtonText("I WANT HELP");
        giveHelpButton.setButtonText("I WANT TO HELP");
        getHelpButton.setOnClick(navToGetHelp);
    }

    Runnable navToGetHelp = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(Main.this, GetHelp.class));
        }
    };
}
