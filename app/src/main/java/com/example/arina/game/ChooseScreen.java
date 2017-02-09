package com.example.arina.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Arina on 22.01.2017.
 */

public class ChooseScreen extends Activity {

    Button magnetic_control;
    Button light_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_chose_screen);

        magnetic_control = (Button) findViewById(R.id.magnet_chose);
        light_control = (Button) findViewById(R.id.light_chose);

        magnetic_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseScreen.this, GameScreen.class);
                intent.putExtra("type_of_control", 0);
                startActivity(intent);
            }
        });

        light_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseScreen.this, GameScreen.class);
                intent.putExtra("type_of_control", 1);
                startActivity(intent);
            }
        });


    }
}
