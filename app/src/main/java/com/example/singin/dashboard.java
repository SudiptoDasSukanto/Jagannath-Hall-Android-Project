package com.example.singin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    static final float END_SCALE = 0.7f ;
    RecyclerView featuredRecycler, mostviewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter1, adapter2;
    private Button signOutbutton;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    private FirebaseAuth mAuth;
    ImageView menuIcon;
    LinearLayout contentView ;

    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        featuredRecycler = findViewById(R.id.featured_recyclerID);
        mostviewedRecycler = findViewById(R.id.mostviewed_recyclerID);
        categoriesRecycler = findViewById(R.id.categories_recyclerID);
        signOutbutton = findViewById(R.id.signoutbuttonID);
        mAuth = FirebaseAuth.getInstance();
        signOutbutton.setOnClickListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_iconID);
        contentView = findViewById(R.id.content);


        navigationDrawer();
        featuredRecycler();
        mostviewedRecycler();
        categoriesRecycler();
    }

    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_homeID);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


       if(item.getItemId() == R.id.nav_all_categoriesID){
           startActivity(new Intent(getApplicationContext(),AllCategories.class));
       }
        return false;
    }

    private void categoriesRecycler() {

        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});

        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesHelperClass> categoriesLocations = new ArrayList<>();
        categoriesLocations.add(new CategoriesHelperClass(gradient1, R.drawable.canteen1, "Canteen"));
        categoriesLocations.add(new CategoriesHelperClass(gradient2, R.drawable.canteen1, "Canteen"));
        categoriesLocations.add(new CategoriesHelperClass(gradient3, R.drawable.canteen1, "Canteen"));

        adapter1 = new CategoriesAdapter(categoriesLocations);
        categoriesRecycler.setAdapter(adapter1);

    }

    private void mostviewedRecycler() {
        mostviewedRecycler.setHasFixedSize(true);
        mostviewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<mvHelperClass> mvLocations = new ArrayList<>();
        mvLocations.add(new mvHelperClass(R.drawable.usa, "USA", "Hlw my name is Sukanto. I am trying to make a Hall managment app."));
        mvLocations.add(new mvHelperClass(R.drawable.india, "India", "Hlw my name is Sukanto. I am trying to make a Hall managment app."));
        mvLocations.add(new mvHelperClass(R.drawable.germany, "Germany", "Hlw my name is Sukanto. I am trying to make a Hall managment app."));

        adapter1 = new mvAdapter(mvLocations);
        mostviewedRecycler.setAdapter(adapter1);
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.flag_ban, "Bangladesh", "Hlw my name is Sukanto. I am trying to make a Hall managment app."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.india, "India", "Hlw my name is Sukanto. I am trying to make a Hall managment app."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.germany, "Germany", "Hlw my name is Sukanto. I am trying to make a Hall managment app."));

        adapter2 = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter2);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.signoutbuttonID) {
            mAuth.signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }


}