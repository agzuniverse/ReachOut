package com.agzuniverse.agz.reachout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

public class CustomButton extends LinearLayout{
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_button, this);
    }

    public void setButtonText(String s){
        Button btn = findViewById(R.id.btn);
        btn.setText(s);
    }
}
