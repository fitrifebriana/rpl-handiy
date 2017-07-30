package com.handiy.handiy.furniture;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handiy.handiy.R;
import com.handiy.handiy.data.TutorialModel;

import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureAdapter.ViewHolder>{
    private Context context;
    private List<Object> furnitureDataSet;
    private FurnitureAdapter.FurnitureItemListener furnitureItemListener;

    public FurnitureAdapter(Context context, List<Object> furnitureDataSet, FurnitureItemListener furnitureItemListener) {
        this.furnitureDataSet = furnitureDataSet;
        this.context = context;
        this.furnitureItemListener = furnitureItemListener;
    }

    @Override
    public FurnitureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_list_item,parent,false);
        return new FurnitureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FurnitureAdapter.ViewHolder holder, int position) {
        TutorialModel tm = (TutorialModel) furnitureDataSet.get(position);
        (holder).tutorialTitle.setText(tm.getTitle());
        Glide.with(context).load(tm.getThumbnail()).into((holder).image);
    }

    @Override
    public int getItemCount() {
        return furnitureDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tutorialTitle;
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tutorialTitle = (TextView)itemView.findViewById(R.id.tutorial_title);
            image = (ImageView) itemView.findViewById(R.id.thumbnail_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            furnitureItemListener.onFurnitureClick(new Gson().toJson(furnitureDataSet.get(getAdapterPosition())));
        }
    }

    public void replaceData(List<Object> furnitureDataSet){
        this.furnitureDataSet = furnitureDataSet;
        notifyDataSetChanged();
    }

    public interface FurnitureItemListener{
        void onFurnitureClick(String extras);
    }
}
