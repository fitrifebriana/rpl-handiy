package com.handiy.handiy.creation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.handiy.handiy.R;
import com.handiy.handiy.data.CreationModel;

import java.util.List;

/**
 * Created by FitriFebriana on 7/1/2017.
 */

public class CreationAdapter extends RecyclerView.Adapter<CreationAdapter.ViewHolder> {

    private Context context;
    private List<Object> creationDataSet;
    private CreationItemClickListener creationItemClickListener;

    public CreationAdapter(Context context, List<Object> creationDataSet, CreationItemClickListener creationItemClickListener) {
        this.context = context;
        this.creationDataSet = creationDataSet;
        this.creationItemClickListener = creationItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new CreationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CreationModel creationData = (CreationModel) creationDataSet.get(position);
        holder.txtCreationTitle.setText(creationData.getTutorial().getTitle());
        Glide.with(context).load(creationData.getTutorial().getThumbnail()).into((holder).imgGridCreation);
    }

    @Override
    public int getItemCount() {
        return creationDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgGridCreation;
        private TextView txtCreationTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imgGridCreation = (ImageView) itemView.findViewById(R.id.imageview_grid);
            txtCreationTitle = (TextView) itemView.findViewById(R.id.tutorial_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public void replaceData(List<Object> creationDataSet){
        this.creationDataSet = creationDataSet;
        notifyDataSetChanged();
    }

    public interface CreationItemClickListener {

    }

}
