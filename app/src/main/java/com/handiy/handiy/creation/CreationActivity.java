package com.handiy.handiy.creation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.handiy.handiy.R;

import java.util.ArrayList;
import java.util.List;

public class CreationActivity extends AppCompatActivity implements CreationContract.View, SwipeRefreshLayout.OnRefreshListener, CreationAdapter.CreationItemClickListener {

    RecyclerView rvCreation;
    CreationAdapter creationAdapter;
    SwipeRefreshLayout srCreation;
    List<Object> creationDataSet = new ArrayList<>();
    Context context = CreationActivity.this;
    CreationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        setupToolbar();
        setupSwipeRefresh();
        setupRecyclerView();

        presenter = new CreationPresenter(this);
        presenter.start();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.creation_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Creations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        creationAdapter = new CreationAdapter(context, new ArrayList<>(), this);
        rvCreation = (RecyclerView)findViewById(R.id.creation_recyclerview);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvCreation.setLayoutManager(layoutManager);
        rvCreation.setAdapter(creationAdapter);
    }

    private void setupSwipeRefresh() {
        srCreation = (SwipeRefreshLayout)findViewById(R.id.creation_swipe_refresh);
        srCreation.setColorSchemeColors(ContextCompat.getColor(CreationActivity.this, R.color.colorPrimaryDark));
        srCreation.setOnRefreshListener(this);
    }

    @Override
    public Context getContext() {
        return CreationActivity.this;
    }

    @Override
    public void showProgress() {
        srCreation.post(new Runnable() {
            @Override
            public void run() {
                srCreation.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        srCreation.post(new Runnable() {
            @Override
            public void run() {
                srCreation.setRefreshing(false);
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        if (getContext() != null && message != null)
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        this.creationDataSet.clear();
        creationAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCreationData(List<Object> creations) {
        this.creationDataSet.clear();
        this.creationDataSet.addAll(creations);
        creationAdapter.replaceData(this.creationDataSet);
    }

    @Override
    public void onRefresh() {
        presenter.loadCreation("idzarunianti");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

