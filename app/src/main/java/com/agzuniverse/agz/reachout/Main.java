package com.agzuniverse.agz.reachout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Main extends Activity {
    private CustomButton getHelpButton;
    private CustomButton giveHelpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHelpButton = findViewById(R.id.getHelpBtn);
        giveHelpButton = findViewById(R.id.giveHelpBtn);



        getHelpButton.setButtonText("I WANT HELP");
        giveHelpButton.setButtonText("I WANT TO HELP");
        getHelpButton.setOnClick(navToGetHelp);
        giveHelpButton.setOnClick(navToGiveHelp);
    }

    Runnable navToGetHelp = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(Main.this, GetHelp.class));
        }
    };
    Runnable navToGiveHelp = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(Main.this, GiveHelp.class));
        }
    };
}
