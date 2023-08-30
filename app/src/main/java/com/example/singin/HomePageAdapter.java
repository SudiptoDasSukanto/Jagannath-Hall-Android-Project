package com.example.singin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.homePageView> {

    Context context ;
    String[] title,desc ;
    int[] images;


    public HomePageAdapter(Context context, String[] title, String[] desc, int[] images) {
        this.context = context;
        this.title = title;
        this.desc = desc;
        this.images = images;
    }

    @NonNull
    @Override
    public homePageView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.designfor_recyclerview_homepage,parent,false);
        return new homePageView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull homePageView holder, int i) {
        holder.titleTextView.setText(title[i]);
        holder.descTextView.setText(desc[i]);
        holder.imageView.setImageResource(images[i]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class homePageView extends RecyclerView.ViewHolder{

            TextView  titleTextView , descTextView ;
            ImageView imageView ;
        public homePageView(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recylerTitleTextviewID);
            descTextView = itemView.findViewById(R.id.recylerDescTextviewID);
            imageView = itemView.findViewById(R.id.recyclerimageViewID);

        }
    }
}
