package com.example.singin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AllCategories extends AppCompatActivity {

    ImageView backPress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        backPress = findViewById(R.id.back_pressedID);

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllCategories.super.onBackPressed();
            }
        });
    }
}