package com.agzuniverse.agz.reachout;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

public class GiveHelp extends AppCompatActivity {
    private CustomButton submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.give_help);

        submit = findViewById(R.id.submit);

        submit.setButtonText("SUBMIT");
        submit.setOnClick(submitTrigger);
    }

    public Runnable submitTrigger = new Runnable() {
        @Override
        public void run() {
            submitData();
        }
    };

    public void submitData() {

        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        public void onSuccess(Location location) {
                            if (location != null) {
                                JSONObject locObj =  new JSONObject();
                                JSONObject userObj = new JSONObject();
                                JSONObject resObj = new JSONObject();
                                JSONObject obj = new JSONObject();
                                try {
                                    locObj.put("latitude",location.getLatitude());
                                    locObj.put("longitude",location.getLongitude());
                                    userObj.put("name", "Vivek R");
                                    userObj.put("bloodgroup", "AB+");
                                    userObj.put("phone", "7890967478");
                                    resObj.put("food", String.valueOf(food));
                                    resObj.put("water", String.valueOf(water));
                                    resObj.put("shelter", String.valueOf(shelter));
                                    resObj.put("blankets", String.valueOf(blanket));
                                    resObj.put("clothes", String.valueOf(cloth));
                                    resObj.put("volunteer", String.valueOf(volunteer));
                                    resObj.put("medical", String.valueOf(medical));
                                    resObj.put("blood", String.valueOf(blood));
                                    obj.put("user", userObj);
                                    obj.put("location", locObj);
                                    obj.put("resources", resObj);
                                    NetworkPost net = new NetworkPost();
                                    net.execute("http://8f555758.ngrok.io/api/posthelpoffer", String.valueOf(obj));
                                    showToast();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
        }

        super.finish();
    }

    public boolean food, water, shelter, cloth, blanket, medical, volunteer, blood;
    public void checkFood(View v) {
        food = ((CheckBox) v).isChecked();
    }
    public void checkWater(View v) {
        water = ((CheckBox) v).isChecked();
    }
    public void checkShelter(View v) {
        shelter = ((CheckBox) v).isChecked();
    }
    public void checkCloth(View v) {
        cloth = ((CheckBox) v).isChecked();
    }
    public void checkBlanket(View v) {
        blanket = ((CheckBox) v).isChecked();
    }
    public void checkMedical(View v) {
        medical = ((CheckBox) v).isChecked();
    }
    public void checkVolunteer(View v) {
        volunteer = ((CheckBox) v).isChecked();
    }
    public void checkBlood(View v) {
        blood = ((CheckBox) v).isChecked();
    }
    public void showToast() {
        Toast.makeText(this, "Request submitted successfully", Toast.LENGTH_LONG).show();
    }
}
