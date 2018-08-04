package com.agzuniverse.agz.reachout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

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

        //Aswinmpr4bhu code here only

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
}
