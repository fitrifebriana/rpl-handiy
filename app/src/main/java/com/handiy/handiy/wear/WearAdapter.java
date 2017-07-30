package com.handiy.handiy.wear;

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

public class WearAdapter extends RecyclerView.Adapter<WearAdapter.ViewHolder>{
    TutorialModel tutorialModel;
    private Context context;
    private List<Object> wearDataSet;
    private WearAdapter.WearItemListener wearItemListener;

    public WearAdapter(Context context, List<Object> wearDataSet, WearItemListener wearItemListener) {
        this.wearDataSet = wearDataSet;
        this.context = context;
        this.wearItemListener = wearItemListener;
    }

    @Override
    public WearAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_list_item,parent,false);
        return new WearAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WearAdapter.ViewHolder holder, int position) {
        tutorialModel = (TutorialModel)wearDataSet.get(position);
        holder.tutorialTitle.setText(tutorialModel.getTitle());
        Glide.with(context).load(tutorialModel.getThumbnail()).into((holder).image);
    }

    @Override
    public int getItemCount() {
        return wearDataSet.size();
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
            wearItemListener.onWearClick(new Gson().toJson(wearDataSet.get(getAdapterPosition())));
        }
    }

    public void replaceData(List<Object> wearDataSet){
        this.wearDataSet = wearDataSet;
        notifyDataSetChanged();
    }

    public interface WearItemListener{
        void onWearClick(String extras);
    }
}
