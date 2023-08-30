package com.example.singin;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    ArrayList<CategoriesHelperClass> categoriesLocation;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> categoriesLocation) {
        this.categoriesLocation = categoriesLocation;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design,parent,false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(view);

        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        CategoriesHelperClass categoriesHelperClass = categoriesLocation.get(position);
        holder.image.setImageResource(categoriesHelperClass.getImage());
        holder.title.setText(categoriesHelperClass.getTitle());
        holder.relativeLayout.setBackground(categoriesHelperClass.getGradientDrawable());

    }

    @Override
    public int getItemCount() {
        return categoriesLocation.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        RelativeLayout relativeLayout ;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.background_gradient);
            image = itemView.findViewById(R.id.categories_imageID);
            title = itemView.findViewById(R.id.categories_titleID);
        }
    }
}


