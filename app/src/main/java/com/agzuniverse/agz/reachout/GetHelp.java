package com.agzuniverse.agz.reachout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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
        String nPpl = ppl.getText().toString();
        String nDesc = desc.getText().toString();


    }

    public boolean food, water, shelter, cloth, blanket, first_aid, medical, rescue;
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
}
