package com.example.singin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {
    private static int spalshscreenTime = 4000;
    Animation topAnimation,buttomAnimation;
    ImageView splashImage;
    TextView startNameOfsplashscreen,descriptionOfsplashscreen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        buttomAnimation = AnimationUtils.loadAnimation(this,R.anim.buttom_animation);
        splashImage = findViewById(R.id.imageViewsplashscreen);
        startNameOfsplashscreen = findViewById(R.id.textView1Splashscreen);
        descriptionOfsplashscreen = findViewById(R.id.textView2Splashscreen);

        splashImage.setAnimation(topAnimation);
        startNameOfsplashscreen.setAnimation(buttomAnimation);
        descriptionOfsplashscreen.setAnimation(buttomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(splashImage,"logo_image");
                pairs[1] = new Pair<View,String>(startNameOfsplashscreen,"logo_text");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(splashScreen.this,pairs);
                startActivity(intent,options.toBundle());
                finish();
            }
        },spalshscreenTime);
    }
}