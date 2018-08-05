package com.agzuniverse.agz.reachout;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

public class GetHelp extends AppCompatActivity {
    private CustomButton submit;
    private EditText ppl;
    private EditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_help);

        submit = findViewById(R.id.submit);
        ppl = findViewById(R.id.ppl);
        desc = findViewById(R.id.desc);

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
        final String nPpl = ppl.getText().toString();
        final String nDesc = desc.getText().toString();

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
                                    userObj.put("bloodgroup", "B+");
                                    userObj.put("phone", "756867299");
                                    resObj.put("food", String.valueOf(food));
                                    resObj.put("water", String.valueOf(water));
                                    resObj.put("shelter", String.valueOf(shelter));
                                    resObj.put("first_aid", String.valueOf(first_aid));
                                    resObj.put("blankets", String.valueOf(blanket));
                                    resObj.put("clothes", String.valueOf(cloth));
                                    resObj.put("food", String.valueOf(food));
                                    resObj.put("medical", String.valueOf(medical));
                                    resObj.put("transport", String.valueOf(transport));
                                    resObj.put("ppl", nPpl);
                                    resObj.put("desc",nDesc);
                                    obj.put("user", userObj);
                                    obj.put("location", locObj);
                                    obj.put("resources", resObj);
                                      NetworkPost net = new NetworkPost();
                                      net.execute("https://postman-echo.com/post", String.valueOf(obj));
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

    public boolean food, water, shelter, cloth, blanket, first_aid, medical, rescue, transport;
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
    public void checkFirstAid(View v) {
        first_aid = ((CheckBox) v).isChecked();
    }
    public void checkMedical(View v) {
        medical = ((CheckBox) v).isChecked();
    }
    public void checkRescue(View v) {
        rescue = ((CheckBox) v).isChecked();
    }
    public void checkTransport(View v) {
        transport = ((CheckBox) v).isChecked();
    }


    public void showToast() {
        Toast.makeText(this, "Request submitted successfully", Toast.LENGTH_LONG).show();
    }
}
