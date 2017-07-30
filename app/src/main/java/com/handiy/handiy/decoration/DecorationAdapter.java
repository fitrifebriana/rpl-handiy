package com.handiy.handiy.decoration;

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

public class DecorationAdapter extends RecyclerView.Adapter<DecorationAdapter.ViewHolder>{
    private Context context;
    private List<Object> decorationDataSet;
    private DecorationAdapter.DecorationItemListener decorationItemListener;


    public DecorationAdapter(Context context, List<Object> decorationDataSet, DecorationActivity decorationItemListener) {
        this.context = context;
        this.decorationDataSet = decorationDataSet;
        this.decorationItemListener = decorationItemListener;
    }

    @Override
    public DecorationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_list_item,parent,false);
        return new DecorationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DecorationAdapter.ViewHolder holder, int position) {
        TutorialModel tm = (TutorialModel) decorationDataSet.get(position);
        (holder).tutorialTitle.setText(tm.getTitle());
        Glide.with(context).load(tm.getThumbnail()).into((holder).image);
    }

    @Override
    public int getItemCount() {
        return decorationDataSet.size();
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
            decorationItemListener.onDecorationClick(new Gson().toJson(decorationDataSet.get(getAdapterPosition())));
        }
    }

    public void replaceData(List<Object> decorationDataSet){
        this.decorationDataSet = decorationDataSet;
        notifyDataSetChanged();
    }

    public interface DecorationItemListener{
        void onDecorationClick(String extras);
    }
}
