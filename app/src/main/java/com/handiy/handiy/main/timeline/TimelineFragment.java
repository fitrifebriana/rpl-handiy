package com.handiy.handiy.main.timeline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.handiy.handiy.R;
import com.handiy.handiy.data.TutorialModel;
import com.handiy.handiy.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class TimelineFragment extends Fragment implements TimelineContract.View, SwipeRefreshLayout.OnRefreshListener, TimelineAdapter.TimelineItemListener{

    private TimelineAdapter timelineAdapter;
    List<TutorialModel> timelineDataSet = new ArrayList<>();
    Context context;
    SwipeRefreshLayout srTimeline;
    SearchView svTimeline;
    TimelineContract.Presenter presenter;
    private SearchView searchTimeline;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    String next_page = null;

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
        @Override
        public RecyclerView.LayoutParams generateDefaultLayoutParams() {
            return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, null);

        initView(view);
        setupSwipeRefresh(view);
        setupRecyclerView(view);
        presenter = new TimelinePresenter(this);
        presenter.start();

        return view;
    }

    private void initView(View view) {
        svTimeline = (SearchView)view.findViewById(R.id.timeline_search);
        svTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search(svTimeline);
            }
        });
    }

    private void setupRecyclerView(View view) {
        timelineAdapter = new TimelineAdapter(context, new ArrayList<TutorialModel>(), this);
        RecyclerView rvTimeline = (RecyclerView) view.findViewById(R.id.tutorial_list);
        rvTimeline.setLayoutManager(linearLayoutManager);
        rvTimeline.setAdapter(timelineAdapter);
//        rvTimeline.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0) {
//                    totalItemCount = linearLayoutManager.getItemCount();
//                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//
//                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold) && next_page != null) {
//                        timelineDataSet.add(null);
//                        timelineAdapter.notifyDataSetChanged();
//                        presenter.loadTimelineData();
//                        isLoading = true;
//                    }
//                }
//            }
//        });
    }

    @Override
    public void showTimelineData(List<TutorialModel> timelines) {
        this.next_page = next_page;
        isLoading = false;
        this.timelineDataSet.clear();
        this.timelineDataSet.addAll(timelines);
        timelineAdapter.replaceData(this.timelineDataSet);
    }

    private void setupSwipeRefresh(View view) {
        srTimeline = (SwipeRefreshLayout) view.findViewById(R.id.timeline_swiperefresh_tutorial);
        srTimeline.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        srTimeline.setOnRefreshListener(this);
    }


    @Override
    public void showError(String message) {
        if (getActivity() != null && message != null)
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

        this.timelineDataSet.clear();
        timelineAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        srTimeline.post(new Runnable() {
            @Override
            public void run() {
                srTimeline.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        srTimeline.post(new Runnable() {
            @Override
            public void run() {
                srTimeline.setRefreshing(false);
            }
        });
    }

    @Override
    public void showTimelineDetailsView(String extras) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("tutorial-detail", extras);
        startActivity(intent);
    }

    @Override
    public void onTimelineClick(String extras) {
        presenter.openTimelineDetails(extras);
    }

    @Override
    public void onRefresh() {
        presenter.loadTimelineData();
    }

    public void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                timelineAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
