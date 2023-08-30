package com.example.singin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mvAdapter extends RecyclerView.Adapter<mvAdapter.mvViewHolder> {

    ArrayList<mvHelperClass> mostviewedLocations ;
    public mvAdapter(ArrayList<mvHelperClass> mvLocations){
        this.mostviewedLocations = mvLocations;
    }

    @NonNull
    @Override
    public mvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design,parent,false);
        mvViewHolder mostViewHolder = new mvViewHolder(view);

        return mostViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull mvViewHolder holder, int position) {

        mvHelperClass mvhelperclass = mostviewedLocations.get(position);
        holder.image.setImageResource(mvhelperclass.getImage());
        holder.title.setText(mvhelperclass.getTitle());
        holder.description.setText(mvhelperclass.getDescription());

    }

    @Override
    public int getItemCount() {
        return mostviewedLocations.size();
    }

    public static class mvViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title , description;

        public mvViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.mv_imageID);
            title = itemView.findViewById(R.id.mv_titleID);
            description = itemView.findViewById(R.id.mv_descID);
        }
    }
}
