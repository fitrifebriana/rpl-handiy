package com.handiy.handiy.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.handiy.handiy.R;
import com.handiy.handiy.data.TutorialModel;

import java.util.List;

/**
 * Created by FitriFebriana on 7/27/2017.
 */

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_TUTORIAL_STEPS = 1;
    public static final int ITEM_TYPE_CREATIONS = 2;

    private Context context;
    private List<Object> stepsDataSet;
    private List<Object> creationsDataSet;
    private DetailItemListener detailItemListener;

    public DetailAdapter(Context context, List<Object> stepsDataSet, DetailItemListener detailItemListener) {
        this.context = context;
        this.stepsDataSet = stepsDataSet;
        this.detailItemListener = detailItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_list_item, parent, false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TutorialModel.TutorialListModel stepsData = (TutorialModel.TutorialListModel) stepsDataSet.get(position);
        ((StepsViewHolder) holder).txtSteps.setText(stepsData.getTutorial());
        if (stepsData.getPhoto() != null)
            Glide.with(context).load(stepsData.getPhoto()).into(((StepsViewHolder) holder).imgSteps);
        else {
            ((StepsViewHolder) holder).imgSteps.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return stepsDataSet.size();
    }

    private class StepsViewHolder extends RecyclerView.ViewHolder {
        TextView txtSteps;
        ImageView imgSteps;

        public StepsViewHolder(View itemView) {
            super(itemView);
            txtSteps = (TextView) itemView.findViewById(R.id.detail_textview_deskripsi_langkah);
            imgSteps = (ImageView) itemView.findViewById(R.id.detail_imageview_langkah);
        }
    }

    public void replaceData(List<Object> stepsDataSet){
        this.stepsDataSet = stepsDataSet;
        notifyDataSetChanged();
    }

    public interface DetailItemListener {

        void onCreationClick(String title);
    }
}
