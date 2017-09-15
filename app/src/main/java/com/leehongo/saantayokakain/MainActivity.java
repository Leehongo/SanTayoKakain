package com.leehongo.saantayokakain;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final int SELECTEDTIME = 2000;
    final int TICKTIME = 1000;

    private ImageView logo;
    private TextView name;
    private Button btnPick, btnRePick;
    private LinearLayout layoutInitial,layoutSelected;
    private ViewFlipper mViewFlipper;

    TypedArray logos,names;
    CountDownTimer cTimer = null;

    private boolean repick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        logo = (ImageView) findViewById(R.id.logo);
        name = (TextView) findViewById(R.id.name);
        btnPick = (Button) findViewById(R.id.btn_pick);
        btnRePick = (Button) findViewById(R.id.btn_repick);
        mViewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        layoutInitial = (LinearLayout) findViewById(R.id.layout_initial);
        layoutSelected = (LinearLayout) findViewById(R.id.layout_selected);


        layoutSelected.setVisibility(View.INVISIBLE);

        btnPick.setOnClickListener(ocl);
        btnRePick.setOnClickListener(ocl);

        Resources res = getResources();
        logos = res.obtainTypedArray(R.array.logos);
        names = res.obtainTypedArray(R.array.names);

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
                    layoutSelected.setVisibility(View.VISIBLE);
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
//
    }
}
