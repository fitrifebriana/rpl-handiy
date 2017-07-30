package com.handiy.handiy.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.handiy.handiy.R;
import com.handiy.handiy.data.CreationModel;

import java.util.List;

/**
 * Created by FitriFebriana on 7/30/2017.
 */

public class CreationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Object> creationsDataSet;
    private DetailItemListener detailItemListener;

    public CreationAdapter(Context context, List<Object> creationsDataSet, DetailItemListener detailItemListener) {
        this.context = context;
        this.creationsDataSet = creationsDataSet;
        this.detailItemListener = detailItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.creation_list_item, parent, false);
        return new CreationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CreationModel creationData = (CreationModel) creationsDataSet.get(position);
        Glide.with(context).load(creationData.getTutorial().getThumbnail()).into(((CreationsViewHolder) holder).imgCreation);
    }

    @Override
    public int getItemCount() {
        return creationsDataSet.size();
    }

    private class CreationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgCreation;

        public CreationsViewHolder(View itemView) {
            super(itemView);
            imgCreation = (ImageView) itemView.findViewById(R.id.creation_imageview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public void replaceData(List<Object> stepsDataSet){
        this.creationsDataSet = stepsDataSet;
        notifyDataSetChanged();
    }

    public interface DetailItemListener {

        void onCreationClick(String title);
    }
}
