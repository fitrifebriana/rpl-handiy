package com.handiy.handiy.main.timeline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handiy.handiy.R;
import com.handiy.handiy.data.TutorialModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> implements Filterable{

    private Context context;
    TutorialModel tutorialModel;
    private List<TutorialModel> timelineDataSet;
    private List<TutorialModel> timelineDataSet2;
    private TimelineItemListener timelineItemListener;


    public TimelineAdapter(Context context, List<TutorialModel> timelineDataSet, TimelineItemListener timelineItemListener) {
        this.timelineDataSet2 = timelineDataSet;
        this.timelineDataSet = timelineDataSet;
        this.context = context;
        this.timelineItemListener = timelineItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        tutorialModel = timelineDataSet2.get(position);
        holder.tutorialTitle.setText(tutorialModel.getTitle());
        Glide.with(context).load(tutorialModel.getThumbnail()).into((holder).image);
    }

    @Override
    public int getItemCount() {
        return timelineDataSet2.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    timelineDataSet2 = timelineDataSet;
                } else {
                    List<TutorialModel> filterList = new ArrayList<>();
                    for (TutorialModel tutorialModel : timelineDataSet) {
                        {
                            if (tutorialModel.getTitle().toLowerCase().contains(charString) ||
                                    tutorialModel.getTitle().contains(charString) ||
                                    tutorialModel.getTitle().toUpperCase().contains(charString)) {
                                filterList.add(tutorialModel);
                            }

                        }
                    }
                    timelineDataSet2 = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = timelineDataSet2;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                timelineDataSet2 = (List<TutorialModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
            timelineItemListener.onTimelineClick(new Gson().toJson(timelineDataSet.get(getAdapterPosition())));
        }
    }

    public void replaceData(List<TutorialModel> timelineDataSet){
        this.timelineDataSet = timelineDataSet;
        this.timelineDataSet2 = timelineDataSet;
        notifyDataSetChanged();
    }

    public interface TimelineItemListener{
        void onTimelineClick(String extras);
    }

}

