package com.leehongo.saantayokakain;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    final int SELECTEDTIME = 2000;
    final int TICKTIME = 1000;

    View layoutInitial, layoutMain;
    private ImageView logo;
    private TextView name;
    private Button btnPick;
    private ImageView btnRePick;
    private ViewFlipper mViewFlipper;

    ArrayList<String> names;
    TypedArray logos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        layoutInitial = findViewById(R.id.include_initial);
        layoutMain = findViewById(R.id.include_main);

        logo = (ImageView) findViewById(R.id.logo);
        name = (TextView) findViewById(R.id.name);
        btnPick = (Button) findViewById(R.id.btn_pick);
        btnRePick = (ImageView) findViewById(R.id.btn_repick);
        mViewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

        btnPick.setOnClickListener(ocl);
        btnRePick.setOnClickListener(ocl);

        logos = getResources().obtainTypedArray(R.array.logos);
        names = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.names)));
    }

    private void selectedItem(){

        int i = new Random().nextInt(names.size());

        String selectedName = names.get(i);
        Drawable selectedImage = ContextCompat.getDrawable(getApplication(),logos.getResourceId(i, 0));
        startAnimation(selectedImage,selectedName);
    }

    private void startAnimation(final Drawable selectedImage, @NonNull final String selectedName){

        new CountDownTimer(SELECTEDTIME, TICKTIME) {

            public void onTick(long millisUntilFinished) {
                //TODO do animate images within x seconds
                logo.setBackground(null);
                name.setText("picking...");
            }

            public void onFinish() {
                logo.setBackground(selectedImage);
                name.setText(selectedName);

                btnRePick.setVisibility(View.VISIBLE);
                this.cancel();
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
                    break;
                case R.id.btn_repick:
                    btnRePick.setVisibility(View.INVISIBLE);
                    break;
            }
            selectedItem();
        }
    };
}


