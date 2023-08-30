package com.example.singin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    int[] images = {
            R.drawable.flag_ban,R.drawable.india,R.drawable.usa,R.drawable.rassia,
            R.drawable.flag_ban,R.drawable.pakistan,R.drawable.germany,R.drawable.flag_ban,
            R.drawable.flag_ban,R.drawable.flag_ban
    };

    String[] title , desc ;
    HomePageAdapter homePageAdapter;
    private Button signOutbutton ;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recyclerView = findViewById(R.id.homePageRecyclerView);
        title = getResources().getStringArray(R.array.countries_name);
        desc = getResources().getStringArray(R.array.countries_desc);
        homePageAdapter = new HomePageAdapter(this,title,desc,images);
        recyclerView.setAdapter(homePageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        signOutbutton = findViewById(R.id.signoutbuttonID);
        mAuth = FirebaseAuth.getInstance();
        signOutbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.signoutbuttonID){
            mAuth.signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}