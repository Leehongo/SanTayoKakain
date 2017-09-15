package com.leehongo.saantayokakain;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final int SELECTEDTIME = 2000;
    final int TICKTIME = 1000;

    View layoutInitial, layoutMain;
    private ImageView logo;
    private TextView name;
    private Button btnPick, btnRePick;
    //private LinearLayout layoutInitial,layoutMain;
    private ViewFlipper mViewFlipper;

    TypedArray logos,names;
    CountDownTimer cTimer = null;

    private boolean repick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        layoutInitial = findViewById(R.id.include_initial);
        layoutMain = findViewById(R.id.include_main);

        logo = (ImageView) findViewById(R.id.logo);
        name = (TextView) findViewById(R.id.name);
        btnPick = (Button) findViewById(R.id.btn_pick);
        btnRePick = (Button) findViewById(R.id.btn_repick);
        mViewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

        layoutMain.setVisibility(View.INVISIBLE);

        btnPick.setOnClickListener(ocl);
        btnRePick.setOnClickListener(ocl);

        logos = getResources().obtainTypedArray(R.array.logos);
        names = getResources().obtainTypedArray(R.array.names);
    }

    private void startAnimation(){

        cTimer = new CountDownTimer(SELECTEDTIME, TICKTIME) {

            Random rand = new Random();
            int i = rand.nextInt(logos.length());

            public void onTick(long millisUntilFinished) {
                //TODO do animate 10 image within x seconds
                logo.setBackground(null);
                name.setText("picking...");
                btnRePick.setVisibility(View.INVISIBLE);
            }

            public void onFinish() {
                Drawable img = ContextCompat.getDrawable(getApplication(),logos.getResourceId(i, 0));
                logo.setBackground(img);
                name.setText(names.getString(i));
                btnRePick.setVisibility(View.VISIBLE);
                cTimer.cancel();
            }

        }.start();
    }

    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch ( view.getId()){
                case R.id.btn_pick:
                    layoutInitial.setVisibility(View.GONE);
                    layoutMain.setVisibility(View.VISIBLE);
                    startAnimation();
                    break;
                case R.id.btn_repick:
                    repick = true;
                    startAnimation();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


