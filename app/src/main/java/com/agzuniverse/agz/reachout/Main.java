package com.agzuniverse.agz.reachout;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
    private CustomButton getHelpButton;
    private CustomButton giveHelpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}
